install.packages("e1071")
library(e1071)

#import training and testing data
train <- read.table(file = "E:/Number Recognition/train.csv", header=TRUE, sep=",")
test <- read.table(file = "E:/Number Recognition/test.csv", header=TRUE, sep=",")

#test set
testX=test[,2:785]

train$label = as.factor(train$label)

#trainning model
NaiveBayes = naiveBayes(label~. , data=train,laplace = 5)

#prediction
pred = predict(NaiveBayes, testX)

#calculate accuracy
accuracy = sum(pred==test$label)/nrow(pred)
accuracy

#save the model
save(NaiveBayes,file = "Model-NaiveBayes.rda")


#################################### Model Evaluation #############################

# truth table calculation
# adjust according to different models

truth_table <- matrix(0,nrow=10,ncol=10)
for(i in seq(0,length(test$label)))
{
  truth_table[as.numeric(pred[i]),test$label[i]+1]<-truth_table[as.numeric(pred[i]),test$label[i]+1]+1
}


# function to calculate precision, recall, f_score
metrics <- function(truth_table){
  precision<-c()
  recall <-c()
  f_score <-c()
  
  rowSum <- rowSums(truth_table)
  colSum <- colSums(truth_table)
  
  for(i in seq(1:10))
  {
    tmp1 <- truth_table[i,i]/rowSum[i]
    precision<-c(precision,tmp1)
    tmp2 <- truth_table[i,i]/colSum[i]
    recall <- c(recall,tmp2)
    tmp <- 2*(tmp1*tmp2)/(tmp1+tmp2)
    f_score<-c(f_score,tmp)
  }
  return(list(prec=precision,rec=recall,score=f_score))
}

r1<- metrics(truth_table)
r1