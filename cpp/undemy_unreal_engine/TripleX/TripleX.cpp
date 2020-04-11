#include <iostream>
#include <ctime>

// Print welcome messages to the terminal
void PrintIntroduction(int Difficulty)
{
    std::cout << "\n\nYou are a secret agent breaking into a level " << Difficulty;
    std::cout << " secure server room... \nEnter the correct codes to continue...\n\n";
  
}

bool PlayGame(int Difficulty)
{
    PrintIntroduction(Difficulty);

    int CodeA = rand() % Difficulty + Difficulty;
    int CodeB = rand() % Difficulty + Difficulty;
    int CodeC = rand() % Difficulty + Difficulty;

    int CodeSum = CodeA + CodeB + CodeC;
    int CodeProduct = CodeA * CodeB * CodeC;

    // Print CodeSum and CodePoduct to the terminal
    std::cout << std::endl;
    std::cout << "+ There are 3 numbers in the code";
    std::cout << "\n+ There codes add-up to: " << CodeSum;
    std::cout << "\n+ There codes multiply to give: " << CodeProduct << std::endl;

    // Store player guess
    int GuessA, GuessB, GuessC;
    std::cin >> GuessA >> GuessB >> GuessC;

    int GuessSum = GuessA + GuessB + GuessC;
    int GuessProduct = GuessA * GuessB * GuessC;

    // Check if the player guess is correct
    if(GuessSum == CodeSum && GuessProduct == CodeProduct)
    {
        std::cout << "\n*** Well done aagent! You have extracted a file! Keep going! ***";
        return true;
    } 
    else
    {
        std::cout << "\n*** You entered the wrong code! Careful agent! Try again! ***";
        return false;
    }
}

int main()
{
    srand(time(NULL)); // create new random sequence based on time of day

    int LevelDifficulty = 1;
    int const MaxDifficulty = 5;

    while(LevelDifficulty <= MaxDifficulty) // loop until all levels are completed
    {
        bool bLevelComplete = PlayGame(LevelDifficulty);

        std::cin.clear(); // Clears any errors
        std::cin.ignore(); // Discards the buffer

        if (bLevelComplete)
        {
            ++LevelDifficulty;
        }   
    }

    std::cout << "\n*** Great work agent! You got all the files! Now get out of there! ***\n";

    return 0;
}