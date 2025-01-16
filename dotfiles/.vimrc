set nocompatible

call pathogen#infect()
syntax on
filetype plugin indent on

colors elflord
set number relativenumber
set encoding=UTF-8

set showcmd
set showmatch

" set relativenumber
set smartcase
set incsearch

" set cursorline
" set cursorlineopt=number
" autocmd ColorScheme * highlight CursorLineNr cterm=bold term=bold gui=bold

" Enables cursor line position tracking:
set cursorline
" Removes the underline causes by enabling cursorline:
"highlight clear CursorLine
" Sets the line numbering to red background:
"highlight CursorLineNR ctermbg=red ctermfg=black

highlight Normal guibg=NONE ctermbg=NONE
highlight EndOfBuffer guibg=NONE ctermbg=NONE

let g:airline_powerline_fonts = 1
let g:airline_theme='solarized_flood'
"let g:airline_solarized_bg='dark'
let g:airline#extensions#tabline#enabled = 1

let g:rainbow_active = 1

"X bug - keep yanked text in system clipboard after vim exit
autocmd VimLeave * call system("xclip -selection clipboard -i", getreg('+'))

map bn :bn<CR>
map bp :bp<CR>

nnoremap <c-s> :w<cr>
inoremap <c-s> <c-o>:w<cr>

"set runtimepath for fzf
set rtp+=/opt/homebrew/opt/fzf

"fzf window toggle down instead of modal screen
let g:fzf_layout = { 'down': '40%'}

" This is the default option:
"   - Preview window on the right with 50% width
"   - CTRL-/ will toggle preview window.
" - Note that this array is passed as arguments to fzf#vim#with_preview function.
" - To learn more about preview window options, see `--preview-window` section of `man fzf`.
let g:fzf_preview_window = ['right:50%', 'ctrl-/']

nnoremap <Leader>p :FZF<CR>
nnoremap <Leader>P :Files<CR>
nnoremap <Leader>F :Rg <C-r><C-w><CR>
nnoremap <Leader>l :Lines <C-r><C-w><CR>

"set spellcheck
"set spell spelllang=en_us

set listchars=eol:^,trail:◦,tab:»»,extends:→,precedes:←,space:·,nbsp:~
"set list

