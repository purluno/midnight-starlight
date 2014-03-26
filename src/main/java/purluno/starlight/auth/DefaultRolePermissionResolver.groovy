package purluno.starlight.auth

import javax.annotation.Resource

import org.apache.shiro.authz.Permission
import org.apache.shiro.authz.permission.RolePermissionResolver
import org.apache.shiro.authz.permission.WildcardPermission

class DefaultRolePermissionResolver implements RolePermissionResolver {
	@Resource
	AuthService authService

	@Override
	Collection<Permission> resolvePermissionsInRole(String roleString) {
		Role role = authService.getRole(roleString)
		if (role == null) {
			[]
		} else {
			role.permissions.collect { new WildcardPermission(it) }
		}
	}
}
