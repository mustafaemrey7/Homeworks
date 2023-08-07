#Assigment2.py
console_screen = """<-----RULES----->
1. BRUSH DOWN
2. BRUSH UP
3. VEHICLE ROTATES RIGHT
4 VEHICLE ROTATES LEFT
5. MOVE UP TO X
6. JUMP
7. REVERSE DIRECTION
8. WIEW THE MATRIX
0. EXIT
"""
print(console_screen)
commands = input("Plese enter the commands with a plus sign (+)  between them.\n").split("+")
global dikey, yatay, yon, isDown, x
dikey, yatay, yon, isDown, x = 1, 1, "r", 0, 0
a = ""
t = 0
commands_debug = commands[1:]
for s in commands_debug:
    if str(s).startswith("5"):
        commands_debug.remove(s)
    elif int(s) < 9 and int(s) >= 0 :
        True
    else:
        commands = input("You entered an incorrect command.Please try again!\n").split("+")
n = int(commands[0]) + 2
board = [["+" for p in range(n)]for p in range(n)]
k = 0
while k < int(commands[0]) :
    k +=1
    for i in board[0]:
        c = 1 
        while c < (int(commands[0])+ 1):
            board[k][c] = " "
            c += 1

for i in commands[1:]:
 
 if i == str(1) : #brush down
     if isDown == 0 : 
         board[dikey][yatay] = "*"
     isDown = 1
     if isDown == 1:
         board[dikey][yatay] = "*"    
 elif i == str(2) : #brush up
    isDown = 0
 
 elif i == str(3) : #turn right
     if yon == "r":
         yon = "d"     
     elif yon == "d":
        yon = "l"
     elif yon =="l":
         yon = "u"   
     elif yon =="u":
         yon = "r" 
 elif i == str(4) : #turn left
     if yon =="r":
         yon = "u"
     elif yon == "d":
         yon = "r"
     elif yon == "l":
         yon = "d"
     elif yon == "u":
         yon = "l"

 elif str(i).startswith("5") : #move up to 5_x
     a = i.replace("5_", "")
     x = int(a)
     if isDown == 0 :
         if yon == "r":
             if yatay == n - 2:
                 yatay = 0 
                 for t in range(0,x):
                     yatay = yatay + 1
                     if yatay == n-2 and t != x - 1 : 
                         yatay = 0
             else:
                 for t in range(0,x):
                     yatay = yatay + 1
                     if yatay == n - 2 and t != x -1 :
                         yatay = 0
         elif yon == "l":
            if yatay == 1 :
                yatay = n-1            
                for t in range(0,x):
                    yatay = yatay - 1
                    if yatay == 1 and t != x - 1:
                        yatay = n - 1            
            else: 
                for t in range(0,x):
                    yatay = yatay - 1
                    if yatay == 1 and t != 1:
                        yatay = n-1
         elif yon == "d":
             if dikey == n-2:
                 dikey = 0
                 for t in range(0,x):
                     dikey = dikey + 1
                     if dikey == n-2 and t != x - 1:
                         dikey = 0
             else:
                 for t in range(0,x):
                     dikey = dikey + 1
                     if dikey == n-2 and t != x -1 :
                         dikey = 0      
         elif yon =="u":
             if dikey == 1:
                 dikey = n-1
                 for t in range(0,x):
                     dikey = dikey - 1
                     if dikey == 1 and t != x - 1 :
                         dikey = n - 1
                
             else:
                 for t in range(0,x):
                     dikey = dikey - 1
                     if dikey == 1 and t != x - 1:
                         dikey = n-1
     else:
         if yon == "r":
             if  yatay == n-2:
                 yatay = 0
                 for t in range(0,x):
                     board[dikey][yatay+1] = "*"
                     yatay = yatay + 1
                     if yatay == n - 2 and t != x - 1:
                         yatay = 0
             else:             
             
                for t in range(0,x):             
                    board[dikey][yatay+1] = "*"
                    yatay = yatay + 1
                    if yatay == n - 2 and t != x - 1:
                        yatay = 0   
         elif yon == "l": 
             if yatay == 1:
                 yatay = n - 1
                 for t in range(0,x):
                     board[dikey][yatay - 1] = "*"
                     yatay = yatay - 1
                     if yatay == 1 and t != x - 1 :
                         yatay = n - 1

             else:
                 for t in range (0,x): 
                     board[dikey][yatay-1] = "*"
                     yatay = yatay - 1
                     if yatay == 1 and t != x-1 :
                         yatay = n - 1          
         
         elif yon == "d":
             if dikey == n - 2:
                 dikey = 0
                 for t in range(0,x):
                     board[dikey+1][yatay] = "*"
                     dikey = dikey + 1
                     if dikey == n -2 and t != x-1:
                         dikey = 0
             else:               
                 for t in range (0,x):
                     board[dikey+1][yatay] = "*"
                     dikey = dikey + 1
                     if dikey == n-2 and t != x-1 :
                         dikey = 0
         
         elif yon == "u":
             if dikey == 1 :
                 dikey = n-1
                 for t in range(0,x):
                     board[dikey-1][yatay] ="*"
                     dikey = dikey - 1
                     if dikey == 1 and t != x - 1:
                         dikey = n-1
             else:
                 for t in range(0,x):
                    board[dikey-1][yatay] = "*"
                    dikey = dikey - 1
                    if dikey == 1 and t != x - 1:
                        dikey = n-1
     t = 0                   
 elif i == str(6) : #Jump 
     isDown = 0
     if yon == "r" or yon =="l":                           
         if  yon == "r":
             if yatay == n-2:
                 yatay = 3
             
             else:
                for t in range(0,3):
                    yatay = yatay + 1
                    if yatay == n - 2 and t != 2:
                        yatay = 0
         else:
             if  yatay == 1:
                  yatay == n-4
             else:
                 for t in range(0,3):
                     yatay = yatay - 1
                     if yatay == 1 and t !=2:
                         yatay = n-1    
     else:
         if  yon == "d" :
             if dikey == n-2 :
                 dikey = 3
             
             else:
                 for t in range(0,3):
                     dikey = dikey + 1
                     if dikey == n-2 and t !=2:
                         dikey = 0
         else:
             if dikey == 1:
                 dikey = n - 4
            
             else:
                 for t in range(0,3):
                     dikey = dikey - 1
                     if dikey == 1 and t !=2:
                         dikey = n-1
     t = 0
 elif i == str(7) : #reverse direciton 
     if yon == "r":
         yon = "l"
     elif yon == "l":
         yon = "r"
     elif yon == "d":
         yon = "u"
     elif yon == "u":
         yon = "d"            
 elif i == str(8) : #view program
    for k in board:
     print(" ".join(k))
 elif i == str(0): #end program
     break

 else:
     print("You have made too much wrong entry please re-start the program") 
     break 

