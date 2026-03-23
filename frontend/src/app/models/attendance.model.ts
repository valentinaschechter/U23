import { User } from './user.model';
import { Activity } from './activity.model';

export interface Attendance {
    id?: number;
    user: User;
    activity: Activity | { id: number };
    isPresent: boolean;
}