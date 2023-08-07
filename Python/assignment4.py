data = []
with open("input.txt","r",encoding="utf-8") as f:
    for line in f:
        line = line.split(" ")
        line[-1] = line[-1].rstrip()
        data.append(line)
score2 = 0
d = 0
for i in data:
    print(" ".join(i))
print(f"Your score is : {score2}")
Stat = True
for x in data:
    for y in x:    
        if y == "B":
            score2 = score2 + 9
        elif y == "G":
            score2 = score2 + 8
        elif y == "W":
            score2 = score2 + 7
        elif y == "Y":
            score2 = score2 + 6
        elif y == "R":
            score2 = score2 + 5        
        elif y == "P":
            score2 = score2 + 4
        elif y == "O":
            score2 = score2 + 3
        elif y == "D":
            score2 = score2 + 2
        elif y == "F":
            score2 = score2 + 1
def Bomber(x,y):
    bombCheck = []
    for i in range(len(data[x])):
        if data[x][i] == "X":
            data[x][i] = " "
            Bomber(x,i)
        else:
            data[x][i] = " "
    for k in range(len(data)):
        if data[k][y] == "X":
            data[k][y] = " "
            Bomber(k,y)
        else:
            data[k][y] = " "
while Stat == True:
    while True:
        try:    
            command = input("Please enter a row and column number: ").split(" ")
            satir = int(command[0])
            sira = int(command[1])
            try:
                if  0<= satir < len(data) and 0<= sira <len(data[0]):
                    if data[satir][sira] != " ":
                        break
                    else:
                        print("Please enter a valid size!")
                else:
                    print("Please enter a valid size!")
            except:
                print(f"Your score is : {score2}")
                print("Game Over")
                exit()
        except:
            print("Please enter a valid size!")
    B = data[satir][sira]
    boomList = []
    boomList.append([satir,sira])   
    past_list = set()
    g = 0
    while True:    
        for line in boomList:
            satir = line[0]
            sira = line[1]
            past_list.add((line[0],line[1]))
            try:
                if data[satir+1][sira] == B and (satir+1,sira) not in past_list:
                    boomList.append([satir+1,sira])
                    g = g + 1
            except:
                pass
            try:    
                if data[satir][sira+1] == B and (satir,sira+1) not in past_list:
                    boomList.append([satir,sira+1])
                    g = g + 1                    
            except:
                pass
            try:
                if data[satir-1][sira] == B and (satir-1,sira) not in past_list:
                    if satir >= 1:
                        boomList.append([satir-1,sira])
                        g = g + 1
            except:
                pass
                    
            try:
                if data[satir][sira-1] == B and (satir,sira-1) not in past_list:
                    if sira >= 1:
                        boomList.append([satir,sira-1])
                        g = g + 1
            except:
                pass
            xCheck = 0
            try:
                if B == "X":
                    Bomber(satir,sira)
                    xCheck = 1
                else:
                    pass
            except:
                pass
        break
    if len(past_list) >= 1 and g>=1 :
        data[satir][sira] = " "
        for i in past_list:
            data[i[0]][i[1]] = " "           
    else:
        if xCheck != 1:
            print("Please enter a valid size!")
    d = 0
    while d<10:
        for i in range(len(data)):
            for c in range(len(data[i])):
                try:
                    if data[i][c] !=  " ":
                        if data[i+1][c] == " ":
                            data[i+1][c] = data[i][c]
                            data[i][c] = " "
                        else:
                            pass
                    else:
                        pass

                except:
                    pass
        d = d + 1
    h = 0
    for i in range(len(data)):
        for k in data:
            try:
                if k[i] == " ":
                    h = h + 1
            except:
                pass
    check_list= []
    possible_commands = ["B","G","W","Y","R","P","O","D","F","X"]
    check = 0 
    times = 0
    while times < 20:    
        try:
            for i in data[0]:
                for k in possible_commands:
                    if k == i :
                        check = check + 1
                    else:
                        pass
            if check == 0:
                data.pop(0)
        except:
            pass
        times = times + 1
    score1 = 0
    y_check = 0
    while y_check<20:
        try:    
            for i in range(len(data[0])):
                sayac = 0
                for k in data:
                    try:
                        if k[i] in possible_commands:
                            pass
                        else:
                            sayac = sayac + 1
                    except:
                        pass
                if sayac == len(data):
                    for k in data:
                        k.pop(i)
        except:
            pass
        y_check = y_check + 1
    for x in data:
        for y in x:    
            if y == "B":
                score1 = score1 + 9
            elif y == "G":
                score1 = score1 + 8
            elif y == "W":
                score1 = score1 + 7
            elif y == "Y":
                score1 = score1 + 6
            elif y == "R":
                score1 = score1 + 5        
            elif y == "P":
                score1 = score1 + 4
            elif y == "O":
                score1 = score1 + 3
            elif y == "D":
                score1 = score1 + 2
            elif y == "F":
                score1 = score1 + 1
    score = score2 - score1
    over = 0
    items = []
    bomb = " "
    for i in range(len(data)):
        for k in range(len(data[i])):
            try:
                items.append(data[i][k])
                if data[i][k] != " ":
                    try:
                        if data[i][k] == data[i][k+1]:
                            over = over + 1        
                    except:
                        pass
                    try:
                        if k >= 1:
                            if data[i][k] == data[i][k-1]:
                                over = over + 1        
                    except:
                        pass
                    try:
                        if data[i][k] == data[i+1][k]:
                            over = over + 1        
                    except:
                        pass
                    try:
                        if i >= 1:
                            if data[i][k] == data[i-1][k]:
                                over = over + 1        
                    except:
                        pass          
                    try:
                        if data[i][k] == "X":
                            over = over + 1
                    except:
                        pass
            except:
                pass
    print(" ")
    for i in data:
        print(" ".join(i))
    print(" ")
    print(f"Your score is : {score}")
    print(" ")
    if over == 0:
        print("Game over!")
        Stat = False
    