if [ $1 == "terminal" ]
then
java --class-path ./bin/ driver.Game
elif [ $1 == "graphic" ]
then
java --module-path ./lib --add-modules=javafx.controls --class-path ./bin/ driver.GraphicalGame
fi