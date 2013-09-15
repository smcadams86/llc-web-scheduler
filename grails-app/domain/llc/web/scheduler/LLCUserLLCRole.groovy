package llc.web.scheduler

import org.apache.commons.lang.builder.HashCodeBuilder

class LLCUserLLCRole implements Serializable {

	LLCUser LLCUser
	LLCRole LLCRole

	boolean equals(other) {
		if (!(other instanceof LLCUserLLCRole)) {
			return false
		}

		other.LLCUser?.id == LLCUser?.id &&
			other.LLCRole?.id == LLCRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (LLCUser) builder.append(LLCUser.id)
		if (LLCRole) builder.append(LLCRole.id)
		builder.toHashCode()
	}

	static LLCUserLLCRole get(long LLCUserId, long LLCRoleId) {
		find 'from LLCUserLLCRole where LLCUser.id=:LLCUserId and LLCRole.id=:LLCRoleId',
			[LLCUserId: LLCUserId, LLCRoleId: LLCRoleId]
	}

	static LLCUserLLCRole create(LLCUser LLCUser, LLCRole LLCRole, boolean flush = false) {
		new LLCUserLLCRole(LLCUser: LLCUser, LLCRole: LLCRole).save(flush: flush, insert: true)
	}

	static boolean remove(LLCUser LLCUser, LLCRole LLCRole, boolean flush = false) {
		LLCUserLLCRole instance = LLCUserLLCRole.findByLLCUserAndLLCRole(LLCUser, LLCRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(LLCUser LLCUser) {
		executeUpdate 'DELETE FROM LLCUserLLCRole WHERE LLCUser=:LLCUser', [LLCUser: LLCUser]
	}

	static void removeAll(LLCRole LLCRole) {
		executeUpdate 'DELETE FROM LLCUserLLCRole WHERE LLCRole=:LLCRole', [LLCRole: LLCRole]
	}

	static mapping = {
		id composite: ['LLCRole', 'LLCUser']
		version false
	}
}
