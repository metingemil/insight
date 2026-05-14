vim.o.number = true
vim.o.relativenumber = true
vim.o.tabstop = 4
vim.o.shiftwidth = 4
vim.o.foldcolumn = "1"
vim.opt.termguicolors = true
vim.o.mousemoveevent = true
--vim.opt.scrolloff = 10
--vim.opt.sidescrolloff = 8
vim.opt.splitbelow = true
vim.opt.splitright = true
vim.opt.timeoutlen = 400
vim.opt.updatetime = 100
vim.opt.cursorline = true
vim.opt.guicursor = "n-v-c:block,i-ci-ve:ver25,r-cr-o:hor20"

vim.o.sessionoptions = "blank,buffers,curdir,folds,help,tabpages,winsize,winpos,terminal,localoptions"
vim.g.loaded_node_provider = 0
vim.g.loaded_perl_provider = 0
vim.g.loaded_python3_provider = 0
vim.g.loaded_ruby_provider = 0

-- Searching behaviors
vim.opt.hlsearch = true
vim.opt.ignorecase = true
vim.opt.smartcase = true

--keymaps
-- Make sure to setup `mapleader` and `maplocalleader` before
-- loading lazy.nvim so that mappings are correct.
-- This is also a good place to setup other settings (vim.opt)
vim.g.mapleader = " "
vim.g.maplocalleader = " "
vim.keymap.set("n", "<leader>pv", vim.cmd.Ex)
vim.keymap.set("n", "bn", ":bn<cr>")
vim.keymap.set("n", "bp", ":bp<cr>")
vim.keymap.set("n", "<C-s>", ":w<cr>")
vim.keymap.set("i", "<C-s>", "<ESC>:w<cr>a") --go one left because when going from insert mode to normal mode the cursor drifts right with one for every change

vim.api.nvim_set_hl(0, "Comment", { fg = "#149cb2", italic = true })

require("config.lazy")

