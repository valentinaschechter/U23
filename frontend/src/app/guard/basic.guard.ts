import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/authService';

export const basicGuard: CanActivateFn = (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    if (authService.getUser() !== null) {
        return true;
    } else {
        console.warn('Toegang geweigerd: Je moet eerst inloggen.');
        router.navigate(['/login']);
        return false;
    }
};