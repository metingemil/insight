vim.o.number = true
vim.o.relativenumber = true
vim.o.tabstop = 4
vim.o.shiftwidth = 4
vim.opt.termguicolors = true
vim.o.mousemoveevent = true
vim.opt.scrolloff = 10
vim.opt.sidescrolloff = 8
vim.opt.splitbelow = true
vim.opt.splitright = true
vim.opt.timeoutlen = 1000
vim.opt.updatetime = 100
vim.opt.cursorline = true

-- Searching behaviors
vim.opt.hlsearch = true
vim.opt.ignorecase = true
vim.opt.smartcase = true


--keymaps
-- Make sure to setup `mapleader` and `maplocalleader` before
-- loading lazy.nvim so that mappings are correct.
-- This is also a good place to setup other settings (vim.opt)
vim.g.mapleader = " "
vim.g.maplocalleader = "\\"
vim.g.mapleader = " "
vim.keymap.set("n", "<leader>pv", vim.cmd.Ex)
vim.keymap.set("n", "bn", ":bn<cr>")
vim.keymap.set("n", "bp", ":bp<cr>")
vim.keymap.set("n", "<C-s>", ":w<cr>")
vim.keymap.set("i", "<C-s>", "<ESC>:w<cr>i")

require("config.lazy")

