import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/authService';

export const adminGuard: CanActivateFn = (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    const user = authService.getUser();

    console.log("DEBUG GUARD - User object:", user); // Wat staat hier?
    console.log("DEBUG GUARD - Rol:", user?.role);

    // Check of de user bestaat EN de juiste rol heeft
    if (user && user.role === 'COACH') {
        return true;
    } else {
        console.warn('Toegang geweigerd: Onvoldoende rechten voor deze pagina.');

        // Als iemand niet is ingelogd, stuur hem naar login. 
        // Als hij wel is ingelogd maar geen coach is, naar home.
        const redirectPath = user ? '/home' : '/login';
        router.navigate([redirectPath]);

        return false;
    }
};