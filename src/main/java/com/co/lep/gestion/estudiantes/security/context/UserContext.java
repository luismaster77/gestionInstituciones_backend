package com.co.lep.gestion.estudiantes.security.context;

public class UserContext {
    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();
    private static final ThreadLocal<Long> currentInstitucionUserId = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> currentUserAdmin = new ThreadLocal<>();
    

    public static void setCurrentUserId(Long userId) {
        currentUserId.set(userId);
    }

    public static Long getCurrentUserId() {
        return currentUserId.get();
    }
    
    public static void setcurrentInstitucionUserId(Long userId) {
    	currentInstitucionUserId.set(userId);
    }

    public static Long getcurrentInstitucionUserId() {
        return currentInstitucionUserId.get();
    }
    
	public static boolean getcurrentUserAdmin() {
		return currentUserAdmin.get();
	}
	
	public static void setcurrentUserAdmin(boolean isAdmin) {
		currentUserAdmin.set(isAdmin);
    }

	public static void clear() {
        currentUserId.remove();
        currentInstitucionUserId.remove();
        currentUserAdmin.remove();
    }
}
