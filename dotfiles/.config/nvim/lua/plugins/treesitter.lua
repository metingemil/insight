return {
	'nvim-treesitter/nvim-treesitter',
    lazy = false,
    build = ':TSUpdate',
	config = function()
		require('nvim-treesitter').install({"regex", "bash", "lua", "vim", "vimdoc", "markdown", "markdown_inline",})
		vim.api.nvim_create_autocmd("FileType", {
        	pattern = { "bash", "sh", "lua", "vim", "help", "markdown", "markdown_inline" },
			callback = function() vim.treesitter.start() end,
      })
    end,
}
