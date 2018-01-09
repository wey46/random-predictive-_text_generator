# random(predictive)_text_generator

#Generating random words with prediction

This is a simple java application which is able to generate random text using words that follow other words to predict the next possible word. The more words you use for the prediction, the more likely the phrase will appear in the training text. 

To play with it, simply compile and run 'MarkovRunner', which will let you specify parameters such as the training file(several example files are provided), the length of the characters for prediction, random seed and etc. 

For example, use "THE TRAGEDY OF ROMEO AND JULIET by William Shakespeare" as training file with 5-charactors-prediction and random seed of 50, the output is: 
    
    "Friar. Holy Saint Francis! What a change is here! Is Rosaline, 
    that thou didst love so dear, So soon forsaken? Young men's love 
    then lies Not truly in their hearts, but in their eyes. Jesu 
    Maria! What a deal of brine Hath wash'd thy sallow cheeks for 
    Rosaline! How much..."

have fun :)
