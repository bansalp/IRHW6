text = []
review = []

text_index = 0
review_index = 1

with  open("train.csv") as f:
    for line in f:
        text.append(line.rsplit(',',1)[text_index])
        review.append(line.rsplit(',',1)[review_index].rstrip())

for a in range(0, len(text)):
	print "text: " + text[a]
	print "review: " + review[a]