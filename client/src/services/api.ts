import { GameState } from "../types";

export async function startGame(player1: string, player2: string, revenge: boolean) {
    console.log("startgame called");
    const response = await fetch("/mancala/api/start", {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            player1: player1,
            player2: player2,
            revenge: revenge,
        }),
    });

    if (response.ok) {
        const gameState = await response.json();
        return gameState as GameState;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function playBowl(index:number) {
    const response = await fetch("/mancala/api/play", {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            indexToPlay: index
        }),
    });

    if (response.ok) {
        const gameState = await response.json();
        return gameState as GameState;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }

}