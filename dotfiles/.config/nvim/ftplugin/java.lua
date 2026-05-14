function jdtlsStart()
	local config = {
		cmd = {'/opt/homebrew/bin/jdtls'},
		root_dir = vim.fs.dirname(vim.fs.find({'gradlew', '.git', 'mvnw'}, { upward = true })[1]),
		settings = {
			java = {
				autobuild = {
					enabled = false
				},
				configuration = {
					updateBuildConfiguration = "interactive",
					maven = {
						userSettings = null
					}
				},
				--[[
				import = {
					gradle = {
						enabled = true
					},
					maven = {
						enabled = true
					},
					exclusions = {
						--"**/target/**",
						--"**/.metadata/**",
						--"**/archetype-resources/**",
						--"**/META-INF/maven/**",
						--"/**/test/**"
					},
				},--]]
			},
		}
	}
	require('jdtls').start_or_attach(config)
end

jdtlsStart()

