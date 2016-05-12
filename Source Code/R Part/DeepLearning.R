install.packages("h2o")
library(h2o)

setwd("E:/Number Recognition")

#import training data
mnistPath <- 'E:/Number Recognition/train-small.csv'
mnist.hex <- h2o.importFile(path = mnistPath, destination_frame = "mnist.hex")
train <- as.data.frame(mnist.hex)
train$label <- as.factor(train$label)
train_h2o <- as.h2o(train)

#training
model <- h2o.deeplearning(x = 2:785,
                          y = 1,
                          training_frame = train_h2o,
                          activation = "RectifierWithDropout",
                          input_dropout_ratio = 0.2,
                          hidden_dropout_ratios = c(0.5,0.5),
                          balance_classes = TRUE, 
                          hidden = c(800,800),
                          epochs = 500)

#import test data
testorigi <- read.table(file = "E:/Number Recognition/test-small.csv", header=TRUE, sep=",")
test_h2o <- h2o.importFile(path = 'E:/Number Recognition/test-small.csv', destination_frame = "test_h2o")
yhat <- h2o.predict(model, test_h2o)
ImageId <- as.numeric(seq(1,1000))
names(ImageId)[1] <- "ImageId"
predictions <- cbind(as.data.frame(ImageId),as.data.frame(yhat[,1]))
names(predictions)[2] <- "Label"
write.table(as.matrix(predictions), file="DNN_pred.csv", row.names=FALSE, sep=",")
accuracy = sum(predictions$Label==testorigi$label)/nrow(testorigi)
accuracy
