import pandas as pd

path = "C:/Users/716705/Documents/GitHub/SudokuCode/sudoku_solution/output/17_clues_hard_times.csv"

file =  open(path, "r")
data = []
for line in file:
	data.append(int(line.strip())/1000000)

df = pd.Series(data)
print (df.describe())