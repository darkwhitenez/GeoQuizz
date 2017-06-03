import json

toDump = {}
country = ""
insideQuestion = False
isCorrect = False
question = ""
answer = ""
questions = []
answersDict = {}
answersArray = []
newLineCnt = 0

toDump["main"] = []

with open('questions.txt', 'r') as f:
  for line in f:
    line = line.strip()

    if line == "":
      if question is not "" and country is not "" and len(answersArray) > 0 and newLineCnt < 2:
        toDump["main"].append({'answers': answersArray, "question": question, "country": country})

      answersArray = []
      newLineCnt += 1
      insideQuestion = False

    elif line[0].isalpha() and insideQuestion:  # answers
      line = line[3:]
      newLineCnt = 0
      if '*' in line:
        isCorrect = True
        line = line[:-2]

      tempDict = {'correct': isCorrect, "text": line}
      answersArray.append(tempDict)

    elif line[0].isdigit():  # question
      line = line[3:]
      newLineCnt = 0
      answersArray = []
      question = line
      insideQuestion = True

    elif line[0].isalpha():
      newLineCnt = 0
      country = line

    if newLineCnt == 2:
      write = True
      newLineCnt = 0

    isCorrect = False

  with open('quest.json', 'w') as fp:
    json.dump(toDump, fp)

"""{
"answers": [
{
"correct": false,
"text": "Dolor sit amet 0"
},
{
"correct": false,
"text": "Dolor sit amet 1"
},
{
"correct": true,
"text": "Dolor sit amet 2"
},
{
"correct": false,
"text": "Dolor sit amet 3"
}
],
"question": {
"text": "Lorem ipsum"
},
"country": "HR"
}"""
