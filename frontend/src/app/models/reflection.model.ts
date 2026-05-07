export interface ReflectionRequest {
    playerId: number;
    rpe: number;
    focus: number;
    selfworth: number;
    groupfeeling: number;
    groupenergy: number;
    learning: number;
    feedback: string;
    positiveNote: string;
}

export interface ReflectionResponse {
    playerId: number;
    submissionDate: Date | string;
    rpe: number;
    focus: number;
    selfworth: number;
    groupfeeling: number;
    groupenergy: number;
    learning: number;
    feedback: string;
    positiveNote: string;
    player?: {
        firstName: string;
        lastName: string;
        username: string;
    };
}