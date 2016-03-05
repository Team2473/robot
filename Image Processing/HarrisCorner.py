'''
Created on Feb 26, 2016

@author: VSatish
'''
# import the necessary packages
import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt

# List of Test Files(w/o .jpg)
testFiles = ["Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7"]

#iterate through files and process each one
for file in testFiles:
    # load image
    imageSource = "/Users/VSatish/Desktop/Robotics/FRC2016BuildSeason/Image Processing/Image Processing Test Files/" + file + ".jpg";
    image = cv.imread(imageSource)
    bwImage = cv.imread(imageSource, cv.IMREAD_GRAYSCALE)
    
    # resize image because otherwise it is too zoomed in
    resized_image = cv.resize(image, (0, 0), fx=0.4, fy=0.4)
    resized_imageBW = cv.resize(bwImage, (0, 0), fx=0.4, fy=0.4)
    
    blur = cv.GaussianBlur(resized_imageBW, (9, 9), 0)
    
    ret, thresh1 = cv.threshold(blur, 254, 255, cv.THRESH_BINARY)
    
    corners = cv.goodFeaturesToTrack(thresh1, 10, .1, 10)
    
    print "Corners: \n", corners
    
    corners = np.int0(corners)
    
    for i in corners:
        x, y = i.ravel()
        cv.circle(resized_image, (x, y), 3, 255, -1)
    0
    cv.imshow('Thresh1', thresh1)    
    cv.imshow('dst', resized_image)
    cv.waitKey(0)

