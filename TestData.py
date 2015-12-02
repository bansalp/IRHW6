text = []
review = []

text_index = 0
review_index = 1

with  open("model.txt") as f:
    for line in f:
        text.append(line.split(',')[text_index])
        review.append(line.split(',')[review_index].rstrip())

for a in range(0, len(text)):
	print "text: " + str(a) + " " + text[a]
	print "review: " + str(a) + " " + review[a]