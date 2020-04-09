#include <iostream>

// Print welcome messages to the terminal
void PrintIntroduction()
{
    std::cout << "\n\nYou are a secret agent breaking into a secure server room\n";
    std::cout << "You need to enter the correct codes to continue...\n\n";
  
}

bool PlayGame()
{
    PrintIntroduction();

    int CodeA = 4;
    int CodeB = 3;
    int CodeC = 2;

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
        std::cout << "\nYou win!" << std::endl;
        return true;
    } 
    else
    {
        std::cout << "\nYou lose!" << std::endl;
        return false;
    }
}

int main()
{
    while(true)
    {
        bool bLevelComplete = PlayGame();

        std::cin.clear(); // Clears any errors
        std::cin.ignore(); // Discards the buffer
    }

    return 0;
}