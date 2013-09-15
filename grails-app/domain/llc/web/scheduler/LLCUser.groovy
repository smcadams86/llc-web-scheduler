package llc.web.scheduler

class LLCUser {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static hasMany = [oAuthIDs: OAuthID]

	static mapping = {
		password column: '`password`'
	}

	Set<LLCRole> getAuthorities() {
		LLCUserLLCRole.findAllByLLCUser(this).collect { it.LLCRole } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
