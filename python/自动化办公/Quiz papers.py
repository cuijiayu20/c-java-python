#encoding = utf-8
import random
capitals = {'Alabama': 'Montgomery', 'Alaska': 'Juneau', 'Arizona': 'Phoenix',
'Arkansas': 'Little Rock', 'California': 'Sacramento', 'Colorado': 'Denver',
'Connecticut': 'Hartford', 'Delaware': 'Dover', 'Florida': 'Tallahassee',
'Georgia': 'Atlanta', 'Hawaii': 'Honolulu', 'Idaho': 'Boise', 'Illinois':
'Springfield', 'Indiana': 'Indianapolis', 'Iowa': 'Des Moines', 'Kansas':
'Topeka', 'Kentucky': 'Frankfort', 'Louisiana': 'Baton Rouge', 'Maine':
'Augusta', 'Maryland': 'Annapolis', 'Massachusetts': 'Boston', 'Michigan':
'Lansing', 'Minnesota': 'Saint Paul', 'Mississippi': 'Jackson', 'Missouri':
'Jefferson City', 'Montana': 'Helena', 'Nebraska': 'Lincoln', 'Nevada':
'Carson City', 'New Hampshire': 'Concord', 'New Jersey': 'Trenton', 'New Mexico':
'Santa Fe','New York': 'Albany', 'North Carolina': 'Raleigh',
'North Dakota': 'Bismarck', 'Ohio': 'Columbus', 'Oklahoma': 'Oklahoma City',
'Oregon': 'Salem', 'Pennsylvania': 'Harrisburg', 'Rhode Island': 'Providence',
'South Carolina': 'Columbia', 'South Dakota': 'Pierre', 'Tennessee':
'Nashville', 'Texas': 'Austin', 'Utah': 'Salt Lake City', 'Vermont':
'Montpelier', 'Virginia': 'Richmond', 'Washington': 'Olympia', 'WestVirginia':
'Charleston', 'Wisconsin': 'Madison', 'Wyoming': 'Cheyenne'}

for Student_text in range(35):
    Question_File = open('.\Question\QuestionPaper.txt %s' %(Student_text+1),'a')
    Answer_File = open('.\Question\Answer\AnswerPaper.txt %s' %(Student_text),'a')
    Question_File.write('Name:\n\nData:\n\nperiop\n\n')
    Answer_File.write('这是第%s张试卷\n\n'%(Student_text+1))
    Question_List=list(capitals.keys())
    random.shuffle(Question_List)
    for QuestionNum in range(50):
        Question_File.write('%s.What is the capitil of %s\n\n' %(QuestionNum,Question_List[QuestionNum]))
        Answer_list=list(capitals.values())
        Correct_Answer=capitals[Question_List[QuestionNum]]
        del Answer_list[Answer_list.index(Correct_Answer)]
        Wrong_answer = random.sample(Answer_list,3)
        Optional_answer=[Correct_Answer]+Wrong_answer
        random.shuffle(Optional_answer)
        for i in range(4):
            Question_File.write(' %s .%s\n\n' %('ABCD'[i],Optional_answer[i]))
        Answer_File.write('%s.%s\n\n' %(QuestionNum,Correct_Answer))
    Answer_File.close()
    Question_File.close()



