const baseUrl = "http://5c4b2a47aa8ee500142b4887.mockapi.io";

export interface Dragon {
    createdAt?: string,
    histories: string[],
    history?: string,
    id?: string,
    name: string,
    type: string
}

export async function getDragons(): Promise<Dragon[]> {
    const response = await fetch(baseUrl + "/api/v1/dragon");

    return response.json();
}


export async function getDragon(id: string) {
    const response = await fetch(baseUrl + `/api/v1/dragon/${id}`);

    return response.json();
}

export async function newDragon(dragon: Dragon) {
    const response = await fetch(baseUrl + `/api/v1/dragon`, {
        method: "POST",
        body: JSON.stringify(dragon)
    });

    return response.json();
}

export async function updateDragon(dragon: Dragon) {
    const response = await fetch(baseUrl + `/api/v1/dragon/${dragon.id}`, {
        method: "PUT",
        body: JSON.stringify(dragon)
    });

    return response.json();
}

export async function removeDragon(id: string) {
    const response = await fetch(baseUrl + `/api/v1/dragon/${id}`, {
        method: "DELETE"
    });

    return response.json();
}