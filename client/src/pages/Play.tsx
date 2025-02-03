import { useMancalaGame } from "../contexts/MancalaGameContext";
import { playBowl, startGame } from "../services/api";
import { Player, isGameState, GameState } from "../types";
import { useState } from "react";

export const Play = () => {
    const { gameState, setGameState } = useMancalaGame();
    if (gameState === undefined) {throw new Error("GameState undefined")}
    const player1: Player = gameState.players[0];
    const player2: Player = gameState.players[1];

    function Bowl({ player, index }: { player: Player; index: number }) {
        const seeds = Seeds(index, player);
    
        const handleClick = async (index: number) => {
            if (seeds > 0 && player.hasTurn && !gameState?.gameStatus.endOfGame) {

                try {
                    const realindex = (player.name === player1.name) ? index : index+7
                    const result = await playBowl(realindex);
    
                    if (isGameState(result)) {
                        setGameState(result);
                    } else {
                        throw new Error("Invalid game state");
                    }
                } catch (error) {
                    console.error("Error handling click:", error);
                }
            }
        };
    
        return (
            <div
                className="w-20 h-20 border-solid rounded-full border-4 border-sogyo p-3 cursor-pointer text-lg flex items-center justify-center"

                onClick={() => handleClick(index)} // Corrected here
                style={{ backgroundColor: (seeds === 0 || !player.hasTurn || gameState?.gameStatus.endOfGame) ? '#e0e0e0' : '#b8dbb4' }} // Disable click if no seeds
            >
            {seeds}
            </div>
        );
    }
    

    function KalahaBowl({ index, player }: { index: number; player: Player }) {
        const seeds = Seeds(index, player);
        return (
            <div
                className="w-20 border-solid rounded-full border-4 border-sogyo p-3 cursor-pointer text-lg flex items-center justify-center"
                
                style={{ backgroundColor: '#e0e0e0' }} // Correctly set backgroundColor here
            >
                {seeds}
            </div>
        );
    }
    
    function Seeds(index: number, player: Player): number {
        return player.pits[index].nrOfStones;
    }

    function GameFinished(gameState: GameState) {
        if (gameState.gameStatus.endOfGame) {
            let winner;
            if (gameState.gameStatus.winner === player1.name) {
                winner = "Player 1: " + player1.name;
            } else if (gameState.gameStatus.winner === player2.name) {
                winner = "Player 2: " + player2.name;
            } else {
                winner = "Draw";
            }

            return (
                <div
                    className="w-[200px] h-20 border-4 border-sogyo rounded-full flex items-center justify-center text-center mx-auto"
                    style={{ backgroundColor: '#e0e0e0' }} 
                >
                    <h2>
                        {winner === "Draw" 
                            ? "Draw!" 
                            : `The winner is: ${winner}`}
                    </h2>
                </div>
            );
        }
        return <></>; 
    }

    function Revenge() {
        const [alert, setAlert] = useState<string | null>(null);
    
        const revengeLogic = async () => {
            if (gameState?.gameStatus.endOfGame) {
                try {
                    const result = await startGame(player1.name, player2.name, true);
                    
                    if (isGameState(result)) {
                        setGameState(result);
                    } else {
                        setAlert(`${result.statusCode} ${result.statusText}`);
                    }
                } catch (error) {
                    setAlert('An error occurred while starting the game.');
                }
            }
        };
        if (gameState?.gameStatus.endOfGame) {
            return (
                <div
                    className="w-20 h-20 border-4 border-sogyo rounded-full flex items-center justify-center text-center mx-auto"
                    style={{ backgroundColor: '#b8dbb4'}}
                    onClick={revengeLogic}
                > <strong>Revenge</strong>
                    {alert && <div className="alert">{alert}</div>}
                </div>
            );
        } else {
            return <></>
        }     
    }
    
    return (
        <div className="`relative h-full w-screen bg-cover bg-center bg-no-repeat p-12 text-center bg-mancala">
            <div className="absolute2 bottom-0 left-0 right-0 top-0 h-full w-full overflow-hidden bg-fixed bg-black/30">
                <div className="h-screen flex flex-col">
                    <div className="flex justify-center mb-2">
                    <h2 className="text-[#b8dbb4]">Player 2: {player2.name}</h2>
                    </div>

                    <div className="flex justify-center">
                        <div className="text-center">
                            <div className="flex flex-row justify-center space-x-4">

                                <KalahaBowl player={player2} index={6} />
                                
                                <div className="flex flex-col space-y-1">
                                    <div className="flex flex-row space-x-1">
                                        <Bowl player={player2} index={5} />
                                        <Bowl player={player2} index={4} />
                                        <Bowl player={player2} index={3} />
                                        <Bowl player={player2} index={2} />
                                        <Bowl player={player2} index={1} />
                                        <Bowl player={player2} index={0} />
                                    </div>

                                    <div className="flex flex-row space-x-1">
                                        <Bowl player={player1} index={0} />
                                        <Bowl player={player1} index={1} />
                                        <Bowl player={player1} index={2} />
                                        <Bowl player={player1} index={3} />
                                        <Bowl player={player1} index={4} />
                                        <Bowl player={player1} index={5} />
                                    </div>
                                </div>

                                <KalahaBowl player={player1} index={6} />
                            </div>
                        </div>
                    </div>

                    <div className="flex justify-center mt-2">
                    <h2 className="text-[#b8dbb4]">Player 1: {player1.name}</h2>
                    </div>
                    <div className="flex space-x-1 justify-center">
                        <div>{GameFinished(gameState)}</div>
                        <div>{Revenge()}</div>
                    </div>


                </div>
                </div>
        </div>
    );
    
    
    
    
};
