
let global_token = "";
let isAuthenticated = window.sessionStorage.getItem("AuthToken")? true : false;

export function login(user: string, password: string) {
    return new Promise(
        (resolve, reject) => {
            setTimeout(() => {
                global_token = "fake_token";
                window.sessionStorage.setItem("AuthToken", "fake_token");
                isAuthenticated = true;
                resolve("fake_token");
            }, 1500);
        }
    )
}


export function isUserAuthenticated() {
    return isAuthenticated;
}