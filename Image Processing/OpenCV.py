#import the necessary packages
import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt

#load image
imageSource = "/Users/VSatish/Desktop/Robotics/FRC2016BuildSeason/Image Processing/Image Processing Test Files/IMG_20160128_150317015.jpg";
image = cv.imread(imageSource)
bwImage = cv.imread(imageSource, cv.IMREAD_GRAYSCALE)

#resize image because otherwise it is too zoomed in
resized_image = cv.resize(image, (0,0), fx=0.4, fy=0.4)
resized_imageBW = cv.resize(bwImage, (0,0), fx=0.4, fy=0.4)

ret,thresh1 = cv.threshold(resized_imageBW,252,255,cv.THRESH_BINARY)

gray = np.float32(thresh1)
dst = cv.cornerHarris(gray,2,3,0.04)

#result is dilated for marking the corners, not important
dst = cv.dilate(dst,None)

# Threshold for an optimal value, it may vary depending on the image.
# thresh1[dst>0.01*dst.max()]=[0,0,255]

cv.imshow('dst',thresh1)
# cv.imshow('black&white', bwImage)
# 
# 
# gray = bwImage
# edges = cv.Canny(gray, 50, 150, apertureSize = 3)
# cv.imshow('canny', edges)
# 
# lines = cv.HoughLines(edges, 1, np.pi/180, 200)
# #print
# #if lines != None:
# print(lines)
# i = 0
# for rho, theta in lines[i]:
#     #print(lines.size)
#     a = np.cos(theta)
#     b = np.sin(theta)
#     x0 = a*rho
#     y0 = b*rho
#     x1 = int(x0 + 1000*(-b))
#     y1 = int(y0 + 1000*(a))
#     x2 = int(x0 - 1000*(-b))
#     y2 = int(y0 - 1000*(a))
#     cv.line(bwImage,(x1,y1),(x2,y2),(0,0,255),2)
#     if i != lines.size -1:
#         i+=1

# #ALL PLOTTING IS DONE HERE!!!
# #convert any non-grayscale images to RGB from BGR
# imageRGB = cv.cvtColor(image, cv.COLOR_BGR2RGB)
# titles = ["Image", "BWImage"]
# images = [imageRGB, bwImage]
# cv.imshow("Hi", bwImage)
# for i in range(1, 3):
#     plt.subplot(1, 2, i)
#     plt.imshow(images[i - 1])
#     plt.title(titles[i - 1])
#     plt.axis("off")
# plt.show()
cv.imshow("Original", resized_image)
cv.imshow("Gray", resized_imageBW)
cv.imshow("Thresh", thresh1)
#when key 0 is pressed, terminate program
cv.waitKey(0)

