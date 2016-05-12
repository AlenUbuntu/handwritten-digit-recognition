# install.packages("devtools")
# install.packages("class")
# install.packages("randomForest")

# load
library("class")
library("randomForest")

# read input
train.data <- read.table(choose.files(),header =T,sep = ",")
test.data <- read.table(choose.files(),header = T,sep=",")

# knn
knn<-knn(train.data[,2:ncol(train.data)],test.data[,2:ncol(test.data)],train.data$label,prob=TRUE,k=1)
knn_acc<-sum(test.data$label==knn)/nrow(test.data)

# randomForest
label1 <- factor(train.data$label,levels =0:9)
rf<-randomForest(label1~.,data=train.data,ntree=20)
rf_pred<-predict(rf,test.data)
randomForest_acc<-sum(rf_pred==test.data$label)/nrow(test.data)


#################################### Model Evaluation #############################

# truth table calculation
# adjust according to different models

truth_table <- matrix(0,nrow=10,ncol=10)
for(i in seq(0,length(test.data$label)))
{
  truth_table[as.numeric(knn[i]),test.data$label[i]+1]<-truth_table[as.numeric(knn[i]),test.data$label[i]+1]+1
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

truth_table <- matrix(0,nrow=10,ncol=10)
for(i in seq(0,length(test.data$label)))
{
  truth_table[as.numeric(rf_pred[i]),test.data$label[i]+1]<-truth_table[as.numeric(rf_pred[i]),test.data$label[i]+1]+1
}

r2<- metrics(truth_table)