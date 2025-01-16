# If you come from bash you might have to change your $PATH.
export PATH=$HOME/bin:/usr/local/bin:$PATH

# Path to your oh-my-zsh installation.
export ZSH="$HOME/.oh-my-zsh"

# Set name of the theme to load --- if set to "random", it will
# load a random theme each time oh-my-zsh is loaded, in which case,
# to know which specific one was loaded, run: echo $RANDOM_THEME
# See https://github.com/ohmyzsh/ohmyzsh/wiki/Themes
ZSH_THEME="agnoster" # set by `omz`

# Set list of themes to pick from when loading at random
# Setting this variable when ZSH_THEME=random will cause zsh to load
# a theme from this variable instead of looking in $ZSH/themes/
# If set to an empty array, this variable will have no effect.
# ZSH_THEME_RANDOM_CANDIDATES=( "robbyrussell" "agnoster" )
# Uncomment the following line to use case-sensitive completion.
# CASE_SENSITIVE="true"

# Uncomment the following line to use hyphen-insensitive completion.
# Case-sensitive completion must be off. _ and - will be interchangeable.
# HYPHEN_INSENSITIVE="true"

# Uncomment one of the following lines to change the auto-update behavior
# zstyle ':omz:update' mode disabled  # disable automatic updates
# zstyle ':omz:update' mode auto      # update automatically without asking
# zstyle ':omz:update' mode reminder  # just remind me to update when it's time

# Uncomment the following line to change how often to auto-update (in days).
# zstyle ':omz:update' frequency 13

# Uncomment the following line if pasting URLs and other text is messed up.
# DISABLE_MAGIC_FUNCTIONS="true"

# Uncomment the following line to disable colors in ls.
# DISABLE_LS_COLORS="true"

# Uncomment the following line to disable auto-setting terminal title.
# DISABLE_AUTO_TITLE="true"

# Uncomment the following line to enable command auto-correction.
# ENABLE_CORRECTION="true"

# Uncomment the following line to display red dots whilst waiting for completion.
# You can also set it to another string to have that shown instead of the default red dots.
# e.g. COMPLETION_WAITING_DOTS="%F{yellow}waiting...%f"
# Caution: this setting can cause issues with multiline prompts in zsh < 5.7.1 (see #5765)
# COMPLETION_WAITING_DOTS="true"

# Uncomment the following line if you want to disable marking untracked files
# under VCS as dirty. This makes repository status check for large repositories
# much, much faster.
# DISABLE_UNTRACKED_FILES_DIRTY="true"

# Uncomment the following line if you want to change the command execution time
# stamp shown in the history command output.
# You can set one of the optional three formats:
# "mm/dd/yyyy"|"dd.mm.yyyy"|"yyyy-mm-dd"
# or set a custom format using the strftime function format specifications,
# see 'man strftime' for details.
# HIST_STAMPS="mm/dd/yyyy"

# Would you like to use another custom folder than $ZSH/custom?
# ZSH_CUSTOM=/path/to/new-custom-folder

# Which plugins would you like to load?
# Standard plugins can be found in $ZSH/plugins/
# Custom plugins may be added to $ZSH_CUSTOM/plugins/
# Example format: plugins=(rails git textmate ruby lighthouse)
# Add wisely, as too many plugins slow down shell startup.
plugins=(sudo
	zsh-autosuggestions
	zsh-syntax-highlighting
	zsh-interactive-cd
	web-search
	copybuffer
	copyfile
	copypath
	docker)

source $ZSH/oh-my-zsh.sh
source ~/.config/zsh-functions.zsh

eval "$(starship init zsh)"

setopt completealiases

alias -s pdf='zathura'
alias -s txt='nvim'
alias -s jpg='imgcat'
alias -s jpeg='imgcat'
alias -s png='imgcat'
alias -s java='nvim'

alias ls='ls --color=auto'
alias ll='eza -alhF --color=always --group-directories-first'
alias la='ls -A'
alias l='ls -CF'
alias grep='grep --color=always'
#alias protoc3='protoc'
#alias ssh_ch_base='ssh -i ~/.ssh/de_ec2_rsa ec2-35-88-229-110.us-west-2.compute.amazonaws.com' #i-08ab3e77a97d8efdf
#alias ssh_ch_mod='ssh -i ~/.ssh/de_ec2_rsa ec2-35-87-112-112.us-west-2.compute.amazonaws.com' #i-01f755ff5eb6de66a
alias ssh_ch='ssh -i ~/.ssh/de_ec2_rsa'
alias scp_ch='scp -i ~/.ssh/de_ec2_rsa'
alias diffs='diff -y -W 250'
alias DE='~/bin/DEctl.sh'
alias delta='delta --syntax-theme dracula --keep-plus-minus-markers --diff-so-fancy --line-numbers'
#alias tmux='tmux new-session -s ch-tmux-session -f ~/.config/tmux/.tmux.conf'

function jshellDE () {
	jshell --class-path $(ls ~/Work/repos/delivery-engine/delivery-engine/target/DeliveryEngine_*/WEB-INF/lib/*.jar | tr '\n' ':') --startup DEFAULT --startup ~/Work/jshell/imports.jsh
}

export EDITOR="vim"
export LESS=-FRX

export MANPAGER="sh -c 'col -bx | bat -l man -p'"

if type brew &>/dev/null
then
  FPATH="$(brew --prefix)/share/zsh/site-functions:${FPATH}"
  autoload -Uz compinit
  compinit
fi

bindkey "ç" fzf-cd-widget

### fzf settings
#source ~/.vim/bundle/vim-fzf/shell/completion.zsh
source ~/.oh-my-zsh/lib/completion.zsh
#source ~/.vim/bundle/vim-fzf/shell/key-bindings.zsh
source ~/.oh-my-zsh/lib/key-bindings.zsh

export FZF_DEFAULT_COMMAND="fd --type f --hidden" 
export FZF_DEFAULT_OPTS="--cycle"
export FZF_CTRL_T_COMMAND=$FZF_DEFAULT_COMMAND
export FZF_CTRL_T_OPTS="--height 80% --preview 'bat --color=always --line-range :50 {}'" 

export FZF_ALT_C_COMMAND="fd --type d . --hidden" 
export FZF_ALT_C_OPTS="--height 80% --preview 'tree -C {} | head -50'"


# User configuration

# export MANPATH="/usr/local/man:$MANPATH"

# You may need to manually set your language environment
# export LANG=en_US.UTF-8

# Preferred editor for local and remote sessions
# if [[ -n $SSH_CONNECTION ]]; then
#   export EDITOR='vim'
# else
#   export EDITOR='mvim'
# fi

# Compilation flags
# export ARCHFLAGS="-arch x86_64"

# Set personal aliases, overriding those provided by oh-my-zsh libs,
# plugins, and themes. Aliases can be placed here, though oh-my-zsh
# users are encouraged to define aliases within the ZSH_CUSTOM folder.
# For a full list of active aliases, run `alias`.
#
# Example aliases
# alias zshconfig="mate ~/.zshrc"
# alias ohmyzsh="mate ~/.oh-my-zsh"

[ -f ~/.fzf.zsh ] && source ~/.fzf.zsh

ssh-add --apple-use-keychain ~/.ssh/github_mac >/dev/null 2>&1
ssh-add --apple-use-keychain ~/.ssh/de_ec2_rsa >/dev/null 2>&1
ssh-add --apple-use-keychain ~/.ssh/gitlab_mac >/dev/null 2>&1

# JAVA versions
#
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
#export JAVA_HOME=~/Java/amazon-corretto-1.8.0_362-jdk-aarch64/Contents/Home
#export JAVA_HOME=~/Java/jdk-17.0.2.jdk-aarch64/Contents/Home
#export JAVA_HOME=~/Java/amazon-corretto-17.0.9.8.1-jdk-aarch64/Contents/Home

#default java 17
export JAVA_HOME=~/Java/amazon-corretto-17.0.9.8.1-jdk-aarch64/Contents/Home

ORIG_PATH=$PATH
PATH_FORMULA='$MAVEN_HOME/bin:$M2_HOME:$CATALINA_HOME:/opt/homebrew/bin:~/bin:$JAVA_HOME/bin:$ORIG_PATH'

alias j8='export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home; eval export PATH=$PATH_FORMULA; java -version'
alias j17='export JAVA_HOME=~/Java/amazon-corretto-17.0.9.8.1-jdk-aarch64/Contents/Home; eval export PATH=$PATH_FORMULA; java -version'
alias j21='export JAVA_HOME=~/Java/amazon-corretto-21.0.0.35.1-jdk-aarch64/Contents/Home; eval export PATH=$PATH_FORMULA; java -version'
alias j23='export JAVA_HOME=~/Java/openjdk-23-jdk-aarch64/Contents/Home; eval export PATH=$PATH_FORMULA; java -version'

#export CATALINA_HOME=/opt/homebrew/Cellar/tomcat@9/9.0.74/libexec
export CATALINA_HOME=/opt/homebrew/opt/tomcat@9/libexec
export MAVEN_HOME=/opt/homebrew/Cellar/maven/3.9.9/libexec
export M2_HOME=~/.m2

eval export PATH=$PATH_FORMULA

#AWS settings
#export AWS_DEFAULT_REGION=eu-west-1
#export AWS_PROFILE=cross-account-transactions-prod

source <(kubectl completion zsh)
eval "$(zoxide init zsh)"
export PATH=/opt/homebrew/bin:$PATH

export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion

