import sys
data_dict = {}
f = sys.argv[1]
commands = sys.argv[2]
with open('smn.txt','r',encoding='utf-8') as f:
    for line in f:
        l = line.split(":")
        data_dict[l[0]] = l[1].split(" ")
for r in data_dict.values():
    r[-1] = r[-1].rstrip()
o = open('output.txt','w',encoding='utf-8')
o.write("Welcome to Assignment 3\n")
o.write("-----------------------------------------------------------------------------------\n")
for i in data_dict.items():
    print(i)


def ANU(username): #Add new user
    if username in data_dict.keys():
        o.write(f"ERROR: Wrong input type! for'ANU'! -- This user already exists!!\n")
    else:
        data_dict[username] = []
        o.write(f"User '{username}' has been added to the social network successfully.\n")
def DEU(x):  #Delete user
   if x in data_dict.keys():
       data_dict.pop(x)
       o.write(f"User '{x}' and his/her all relations have been deleted successfully.\n")
       for i in data_dict.values():
           if x in i:
               i.remove(x)
               
   else:
       o.write(f"Error: Wrong input type! for 'DEU'!--There is no user named '{x}' !!\n")
def ANF(x,y): #Add new friend
    if x in data_dict.keys() and y in data_dict.keys():    
        if x not in data_dict[y] and y not in data_dict[x]:
            data_dict[y].append(x)
            data_dict[x].append(y)
            o.write(f"Relation between '{x}' and '{y}' has been added successfully\n")
        else:
            o.write(f"ERROR: A relation between '{y}' and '{x}' already exists!!\n")
    
    elif x in data_dict.keys() and y not in data_dict.keys():
        o.write(f"ERROR: Wrong input type! for 'ANF'! -- No user named '{y}' found!\n")
    elif y in data_dict.keys() and x not in data_dict.keys():
        o.write(f"ERROR: Wrong input type! for 'ANF'! -- No user named '{x}' found!\n")
    else:
        o.write(f"ERROR: Wrong input type! for 'ANF'! -- No user named '{x}' and '{y}' found!\n")

def DEF(x,y):
    if x in data_dict.keys() and y in data_dict.keys():
        if x in data_dict[y] and y in data_dict[x]:
            data_dict[y].remove(x)
            data_dict[x].remove(y)
            o.write(f"Relation between '{x}' and '{y}' has been deleted successfully\n")
        else:
            o.write(f"ERROR: No relation between '{x} and '{y} found!\n")
    elif x in data_dict.keys() and y not in data_dict.keys():
        o.write(f"ERROR: Wrong input type! for 'DEF'! -- No user named '{y}' found!\n")
    elif y in data_dict.keys() and x not in data_dict.keys():
        o.write(f"ERROR: Wrong input type! for 'DEF'! -- No user named '{x}' found!\n")
    else:
        o.write(f"ERROR: Wrong input type! for 'DEF'! -- No user named '{x}' and '{y}' found!\n")
    return
def CF(x):
    if x in data_dict.keys():
        o.write(f"User '{x}' has {len(data_dict[x])} friends.\n")
    else:
        o.write(f"ERROR: Wrong input type! for 'CF'! -- No user named '{x}' found!\n")

def FPF(x,y):
    if x in data_dict.keys():        
        if y == "1":
            o.write(f"User '{x}' has '{len(data_dict[x])}'possible friends when maximum distance is '{y}':\n")
            o.write(f"These possible friends: {data_dict[x]}\n")
        elif y == "2":
            fpf2 = []
            for i in data_dict[x]:
                fpf2.append(i)
            for user in data_dict[x]:
                for friend in data_dict[user]:
                    fpf2.append(friend)
            fpf2 = set(fpf2)
            if x in fpf2 :
                fpf2.remove(x)
            fpf2 = sorted(list(fpf2))
            o.write(f"User '{x}' has '{len(fpf2)}' possible friends when maximum distance is '{y}':\n")       
            o.write(f"These possible friends: {fpf2}\n")

        elif y == "3":
            fpf3 = []
            for i in data_dict[x]:
                fpf3.append(i)

            for user in data_dict[x]:
                for friend in data_dict[user]:
                    fpf3.append(friend)

            for user in data_dict[x]:
                for friend in data_dict[user]:
                    for k in data_dict[friend]:
                        fpf3.append(k)
            fpf3 = set(fpf3)
            fpf3.remove(x)
            fpf3 = sorted(list(fpf3))
            o.write(f"User '{x}' has '{len(fpf3)}' possible friends when maximum distance is {y}:\n")
            o.write(f"These possible friends: {fpf3}\n")
    else:
        o.write(f"ERROR: Wrong input type! for 'FPF'! -- No user named '{x}' found!\n") 
def SF(x,y):
    data = []
    data2 = []
    data3 = []
    if x in data_dict.keys():
        if 1<int(y) and int(y)<4:
            for user in data_dict[x]:
                for first in data_dict[user]:
                    data.append(first)
            data.remove(x)
            if y == "2":
                for eleman in data:
                    if data.count(eleman) == 2:
                        if eleman != x:
                            data2.append(eleman)

                for eleman in data:
                    if data.count(eleman) == 3:
                        if eleman != x:
                            data3.append(eleman)
                data2 = set(data2)
                data3 = set(data3)
                data4 = data2.union(data3)      
                o.write(f"Suggestion List for '{x}' (when MD is {y}):\n")
                for i in data2:
                    o.write(f"'{x}' has 2 mutual friends with '{i}'\n")
                for i in data3:
                    o.write(f"'{x}' has 3 mutual friends with '{i}'\n")
                
                o.write(f"The suggested friends for '{x}' :{data4}\n")
            elif y == "3":
                for eleman in data:
                    if data.count(eleman) == 3:
                        if eleman != x:
                            data3.append(eleman)
                data3 = set(data3)     
                data3 = sorted(list(data3))

                o.write(f"Suggestion List for '{x}'(When MD is {y}):\n")
                for i in data3:
                    o.write(f"'{x}' has 3 mutual friends with '{i}'\n")
                o.write(f"The suggested friends for '{x}' : {data3}\n")        
        else:            
            o.write("ERROR: Mutually degree cannot be less than 1 or greater than 4\n")

    else:
        o.write(f"ERROR: Wrong input type! for 'SF' -- No user '{x}'' found!!\n")
with open('commands.txt',mode='r',encoding='utf-8') as commands:
    for k in commands:
        work = list(k.split(" "))
        if len(work) == 2 :
            work[1] = work[1].rstrip()
            if work[0] == "ANU":
                ANU(work[1])

            elif work[0] == "DEU":
                DEU(work[1])

            elif work[0] == "CF":
                CF(work[1])

        elif len(work) == 3 :
            work[2] = work[2].rstrip() 
            if work[0] == "ANF":
                ANF(work[1],work[2])

            elif work[0] == "DEF":
                DEF(work[1],work[2])
            elif work[0] == "FPF":
                FPF(work[1],work[2])

            elif work[0] == "SF":
                SF(work[1],work[2])
for i in data_dict.items():
    print(i)