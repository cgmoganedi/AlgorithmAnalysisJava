import matplotlib.pyplot as plt
import sys

file = sys.argv[1]
#title = sys.argv[2]
n = range(int(sys.argv[1]))
title = sys.argv[int(sys.argv[1])+2]

first = False
for i in n:
    if first:
        plt.plotfile(sys.argv[i+2], ('clues', 'time'), newfig= False, label = sys.argv[i+2][:-4])        
    else:
        first = True
        plt.plotfile(sys.argv[i+2], ('clues', 'time'), label = sys.argv[i+2][:-4])



#plt.plotfile(file, ('input', 'time'))
plt.xlabel('number of clues given')
plt.ylabel('log base 2 the average nano time (ns)')
plt.title(title);
axes = plt.gca()
axes.set_xlim([17,37])
axes.set_ylim([0,30])
plt.legend(shadow = True, fancybox = True)
plt.savefig(title +'.jpg')