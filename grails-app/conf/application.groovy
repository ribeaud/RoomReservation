

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'roomreservation.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'roomreservation.UserRole'
grails.plugin.springsecurity.authority.className = 'roomreservation.Role'

grails.plugin.springsecurity.securityConfigType = 'Annotation'
// Extra rules that cannot be mapped using annotations
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/h2-console/**',  access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

// The easiest possibility to open non-annotated controllers
// grails.plugin.springsecurity.rejectIfNoRule = false
// grails.plugin.springsecurity.fii.rejectPublicInvocations = false
