import { USER_TYPE } from "../services/constants";

export class UserDocument {
    userProfile = USER_TYPE;
    upk: number;
    type: string;
    uuid: string;
    content: string;
    format: string;
    size: string;
    name: string;
}