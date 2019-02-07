;  Executable name : SHOWCHAR
;  Version         : 1.0
;  Created date    : 5/11/2009
;  Last update     : 5/15/2009
;  Author          : Jeff Duntemann
;  Description     : A simple program in assembly for Linux, using NASM 2.05,
;    demonstrating discontinuous string writes to memory using STOSB without
;    REP. The program loops through characters 32 through 255 and writes a
;    simple "ASCII chart" in a display buffer. The chart consists of 8 lines
;    of 32 characters, with the lines not continuous in memory.
;
;    Note that the output to the console from this program will NOT display
;    correctly unless you have enabled the IBM850 character encoding in
;    the terminal program being used to display the console! For more on
;    this issue, and how to enable IBM850, see p. 177 in the book.
;
;  Build using these commands:
;    nasm -f elf -g -F stabs showchar.asm
;    ld -o showchar showchar.o
;

SECTION .data			; Section containing initialised data
	EOL 	equ 10		; Linux end-of-line character
	FILLCHR	equ 32		; Default to ASCII space character
	CHRTROW	equ 2		; Chart begins 2 lines from top
	CHRTLEN	equ 32		; Each chart line shows 32 chars
; This escape sequence will clear the console terminal and place the
; text cursor to the origin (1,1) on virtually all Linux consoles:
	ClrHome db 27,"[2J",27,"[01;01H"
	CLRLEN	equ $-ClrHome	; Length of term clear string
	
SECTION .bss			; Section containing uninitialized data	

	COLS	equ 81		; Line length + 1 char for EOL
	ROWS	equ 25		; Number of lines in display
	VidBuff	resb COLS*ROWS	; Buffer size adapts to ROWS & COLS

SECTION .text			; Section containing code

global 	_start			; Linker needs this to find the entry point!

; This macro clears the Linux console terminal and sets the cursor position
; to 1,1, using a single predefined escape sequence.
%macro	ClearTerminal 0
	pushad			; Save all registers
	mov eax,4		; Specify sys_write call
	mov ebx,1		; Specify File Descriptor 1: Standard Output
	mov ecx,ClrHome		; Pass offset of the error message
	mov edx,CLRLEN		; Pass the length of the message
	int 80H			; Make kernel call
	popad			; Restore all registers
%endmacro

;-------------------------------------------------------------------------
; Show: 	Display a text buffer to the Linux console
; UPDATED: 	5/13/2009
; IN: 		Nothing
; RETURNS:	Nothing
; MODIFIES: 	Nothing
; CALLS:	Linux sys_write
; DESCRIPTION:	Sends the buffer VidBuff to the Linux console via sys_write.
;		The number of bytes sent to the console is calculated by
;		multiplying the COLS equate by the ROWS equate.

Show:	pushad			; Save all registers
	mov eax,4		; Specify sys_write call
	mov ebx,1		; Specify File Descriptor 1: Standard Output
	mov ecx,VidBuff		; Pass offset of the buffer
	mov edx,COLS*ROWS	; Pass the length of the buffer
	int 80H			; Make kernel call
	popad			; Restore all registers
	ret			; And go home!


;-------------------------------------------------------------------------
; ClrVid: 	Clears a text buffer to spaces and replaces all EOLs
; UPDATED: 	5/13/2009
; IN: 		Nothing
; RETURNS:	Nothing
; MODIFIES: 	VidBuff, DF
; CALLS:	Nothing
; DESCRIPTION:	Fills the buffer VidBuff with a predefined character
;		(FILLCHR) and then places an EOL character at the end
;		of every line, where a line ends every COLS bytes in
;		VidBuff.

ClrVid:	push eax		; Save caller's registers
	push ecx
	push edi
	cld			; Clear DF; we're counting up-memory
	mov al,FILLCHR		; Put the buffer filler char in AL
	mov edi,VidBuff		; Point destination index at buffer
	mov ecx,COLS*ROWS	; Put count of chars stored into ECX
	rep stosb		; Blast chars at the buffer
; Buffer is cleared; now we need to re-insert the EOL char after each line:
	mov edi,VidBuff		; Point destination at buffer again
	dec edi			; Start EOL position count at VidBuff char 0
	mov ecx,ROWS		; Put number of rows in count register
PtEOL:	add edi,COLS		; Add column count to EDU
	mov byte [edi],EOL	; Store EOL char at end of row
	loop PtEOL		; Loop back if still more lines
	pop edi			; Restore caller's registers
	pop ecx
	pop eax
	ret			; and go home!

;-------------------------------------------------------------------------
; Ruler: 	Generates a "1234567890"-style ruler at X,Y in text buffer
; UPDATED: 	5/13/2009
; IN: 		The 1-based X position (row #) is passed in EBX
;		The 1-based Y position (column #) is passed in EAX
;		The length of the ruler in chars is passed in ECX
; RETURNS:	Nothing
; MODIFIES: 	VidBuff
; CALLS:	Nothing
; DESCRIPTION:	Writes a ruler to the video buffer VidBuff, at the 1-based
;		X,Y position passed in EBX,EAX. The ruler consists of a
;		repeating sequence of the digits 1 through 0. The ruler
;		will wrap to subsequent lines and overwrite whatever EOL
;		characters fall within its length, if it will noy fit
;		entirely on the line where it begins. Note that the Show
;		procedure must be called after Ruler to display the ruler
;		on the console.

Ruler:  push eax	; Save the registers we change
	push ebx
	push ecx
	push edi
	mov edi,VidBuff	; Load video address to EDI
	dec eax		; Adjust Y value down by 1 for address calculation
	dec ebx		; Adjust X value down by 1 for address calculation
	mov ah,COLS	; Move screen width to AH
	mul ah		; Do 8-bit multiply AL*AH to AX
	add edi,eax	; Add Y offset into vidbuff to EDI
	add edi,ebx	; Add X offset into vidbuf to EDI
; EDI now contains the memory address in the buffer where the ruler
; is to begin. Now we display the ruler, starting at that position:
        mov al,'1'	; Start ruler with digit '1'
DoChar: stosb		; Note that there's no REP prefix!
	add al,'1'	; Bump the character value in AL up by 1
        aaa		; Adjust AX to make this a BCD addition
	add al,'0'	; Make sure we have binary 3 in AL's high nybble
        loop DoChar	; Go back & do another char until ECX goes to 0
	pop edi		; Restore the registers we changed
	pop ecx
	pop ebx
	pop eax
	ret		; And go home!

;-------------------------------------------------------------------------
; MAIN PROGRAM:
	
_start:
	nop		; This no-op keeps gdb happy...

; Get the console and text display text buffer ready to go:
	ClearTerminal	; Send terminal clear string to console
	call ClrVid	; Init/clear the video buffer

; Show a 64-character ruler above the table display:
	mov eax,1	; Start ruler at display position 1,1
	mov ebx,1
	mov ecx,32	; Make ruler 32 characters wide
	call Ruler	; Generate the ruler

; Now let's generate the chart itself:
	mov edi,VidBuff		; Start with buffer address in EDI
	add edi,COLS*CHRTROW	; Begin table display down CHRTROW lines
	mov ecx,224		; Show 256 chars minus first 32
	mov al,32		; Start with char 32; others won't show
.DoLn:	mov bl,CHRTLEN		; Each line will consist of 32 chars
.DoChr:	stosb			; Note that there's no REP prefix!
	jcxz AllDone		; When the full set is printed, quit
	inc al			; Bump the character value in AL up by 1
	dec bl			; Decrement the line counter by one
	loopnz .DoChr	; Go back & do another char until BL goes to 0
	add edi,COLS-CHRTLEN	; Move EDI to start of next line
	jmp .DoLn		; Start display of the next line

; Having written all that to the buffer, send the buffer to the console:
AllDone:
	call Show	; Refresh the buffer to the console

Exit:	mov eax,1	; Code for Exit Syscall
	mov ebx,0	; Return a code of zero	
	int 80H		; Make kernel call
