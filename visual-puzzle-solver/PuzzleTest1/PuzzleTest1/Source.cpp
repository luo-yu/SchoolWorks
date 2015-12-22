#include <opencv2/opencv.hpp>
#include <iostream>
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <algorithm>

using namespace cv;
using namespace std;
/// Global variables
int threshold_value = 0;
int threshold_type = 3;;
int const max_value = 255;
int const max_type = 4;
int const max_BINARY_value = 255;

RNG rng(12345);
int thresh = 100;

Mat src, src_gray, dst, sol, statesMasked;
char* window_name = "Threshold Demo";

char* trackbar_type = "Type: \n 0: Binary \n 1: Binary Inverted \n 2: Truncate \n 3: To Zero \n 4: To Zero Inverted";
char* trackbar_value = "Value";

/// Function headers
void Threshold_Demo( int, void* );
void Contour_Demo(int, void*);
vector<vector<Point>> getPieces(vector<vector<Point>> contours);

/**
 * @function main
 */
int main( int argc, char** argv )
{
  /// Load an image
	cout << "Please enter in the complete file path for the pieces image:\n";
	string pieces;
	std::getline (std::cin, pieces);
	cout << "Please enter in the complete file path for the complete image:\n";
	string solved;
	std::getline (std::cin, solved);
	/*char* pieces = "C:\\Users\\Steven\\Downloads\\Photos\\states.jpg";
	char* solved = "C:\\Users\\Steven\\Downloads\\Photos\\StatesCompleted.jpg";*/

	pieces.erase (std::remove(pieces.begin(), pieces.end(), '\n'), pieces.end());
	pieces.erase (std::remove(pieces.begin(), pieces.end(), '\"'), pieces.end());
	solved.erase (std::remove(solved.begin(), solved.end(), '\n'), solved.end());
	solved.erase (std::remove(solved.begin(), solved.end(), '\"'), solved.end());

	std::cout << "Loading images..." << std::endl;
	src = imread(pieces);
	statesMasked = imread(pieces);
	sol = imread(solved);

	/// Convert the image to Gray
	cvtColor( src, src_gray, CV_BGR2GRAY );

	 // createTrackbar(" Canny thresh:", "Source", &thresh, max_value, Contour_Demo);
	  Contour_Demo(0, 0);

	  waitKey(0);
	  return(0);
  
	/// Wait until user finishes program
	while(true)
	{
		int c;
		c = waitKey( 20 );
		if( (char)c == 27 )
		{ break; }
	}
}


/**
 * @function Threshold_Demo
 */
void Threshold_Demo( int, void* )
{
  /* 0: Binary
     1: Binary Inverted
     2: Threshold Truncated
     3: Threshold to Zero
     4: Threshold to Zero Inverted
   */

  threshold( src_gray, dst, threshold_value, max_BINARY_value,threshold_type );

  //imshow( window_name, dst );
}


/** @function contour_demo */
void Contour_Demo(int, void*)
{
	Mat canny_output;
	vector<vector<Point>> contours;
	vector<vector<Point>> states;
	vector<vector<Point>> currentVector;
	vector<Vec4i> hierarchy;

	// Threshold the gray image to black and white
	std::cout << "Thresholding..." << std::endl;
	threshold( src_gray, dst, 254, max_BINARY_value, THRESH_BINARY );

	Mat drawing; // Convert back from gray to color
	cvtColor(dst, drawing, CV_GRAY2BGR);  //Mat::zeros(canny_output.size(), CV_8UC3);

	std::cout << "Finding contours..." << std::endl;
	/// Find contours on the thresholded image
	findContours(dst, contours, RETR_LIST, CV_CHAIN_APPROX_SIMPLE);

	int count = 0;

	std::cout << "Getting pieces..." << std::endl;
	// Get the contours of our puzzle pieces
	states = getPieces(contours);

	vector<vector<Point>> transformedPolys;
	int bad = 0;
	bool done = false;
	currentVector = states;
	int multiplier = 1;
	
	std::cout << "Looking for matches..." << std::endl;
	// Keep trying to find matches, increasing the number of features to look at each time.
	while( !done ) {
		std::cout << "Round " << multiplier << " of matching." << std::endl;
		int size = currentVector.size();
		vector<int> deletedVals;
		// Run the matching and contour drawing on each piece in the current vector.
		for( int i = 0; i < size ; i++ ) {
			const int   NFEATURES   = 500*multiplier;   // Per tile, NOT the entire image!
			const int	NFEATURESFULL = 20000;
			const float SCALEFACTOR = 1.2f;
			const int   NLEVELS     = 1;
			const int   PATCH_SIZE  = 80;
			const int	EDGE_THRESH = PATCH_SIZE*2;

			Mat mask = Mat::zeros(drawing.size(), CV_8U);
			Scalar color = Scalar(255,255,255);
			drawContours(mask, currentVector, i, color, CV_FILLED);

			Ptr<ORB>	featureStuff = ORB::create(NFEATURES, SCALEFACTOR, NLEVELS, EDGE_THRESH, 0, 2, 0, PATCH_SIZE);
			Ptr<ORB>	featureStuffFull = ORB::create(NFEATURESFULL, SCALEFACTOR, NLEVELS, EDGE_THRESH, 0, 2, 0, PATCH_SIZE);

			vector<KeyPoint> kp1, kp2;
			Mat desc1, desc2;

			// Find keypoints and descriptors in the piece and the full puzzle
			featureStuff->detectAndCompute(src, mask, kp1, desc1);
			featureStuffFull->detectAndCompute(sol, NULL, kp2, desc2);

			// Use the brute force matcher to find matching keypoint descriptors in the piece and the puzzle
			BFMatcher bf = BFMatcher(NORM_HAMMING);
			std::vector<DMatch> matches;
			bf.match(desc1, desc2, matches);

			vector<Point> obj;
			vector<Point> scene;

			double max_dist = 0; double min_dist = 100;
			// Quick calculation of max and min distances between keypoints
			for( int j = 0; j < desc1.rows; j++ )
			{ double dist = matches[j].distance;
				if( dist < min_dist ) min_dist = dist;
				if( dist > max_dist ) max_dist = dist;
			}

			std::vector< DMatch > goodMatches;
			// Generate a vector of matches within a range of 3 times the closest distance.
			for( int j = 0; j < desc1.rows; j++ ) {
				if( matches[j].distance < 40) {
					goodMatches.push_back(matches[j]);
				}
			}

			// Push the good keypoints from the piece and the puzzle into their own vectors
			for( int j = 0; j < goodMatches.size(); j++ ) {
				obj.push_back(kp1[goodMatches[j].queryIdx].pt);
				scene.push_back(kp2[goodMatches[j].trainIdx].pt);
			}

			Mat tempMask;
			// Commented out test code for future use
			// Mat H = findHomography(obj, scene, CV_RANSAC, 3.0, tempMask);
			// Mat H = findHomography(obj, scene, CV_RANSAC );

			// Get the transformation matrix from the keypoints.
			Mat H = estimateRigidTransform(obj, scene, false);
			// If there is no good transformation matrix found do not attempt to use the transformation and back out of the for loop.
			// We will attempt in the next go around of the while loop.
			if( H.rows == 0 ) {
				bad++;
				//std::cout << "Bad piece: " << i << std::endl;
				continue;
			}

			// Use the states full contour to create an approximate polygon representing the shape.
			// This is done to reduce the number of points from hundreds down to dozens.
			vector<Point> poly;
			approxPolyDP(currentVector[i], poly, 5, true);
			vector<Point> polyWarped;

			// Translate and rotate the approximated polygon using the transformation matrix and add it to the transformedPolys vector.
			transform(poly, polyWarped, H);
			transformedPolys.push_back(polyWarped);
		
			//std::cout << "State " << i << " drawn." << std::endl;
			// Push the piece number that we've drawn into deletedVals so that we can remove it from currentVector.
			deletedVals.push_back(i);
			// If we have the same number of transformed polygons as we have actual pieces, then we have moved all our pieces.
			if( transformedPolys.size() == states.size() )
				done = true;
			done = true;
		}
		// Remove the drawn piece from currentVector
		for( int i = deletedVals.size()-1; i >= 0; i-- ) {
			currentVector.erase(currentVector.begin() + deletedVals[i]);
		}
		// empty deletedVals so we can re-use it
		deletedVals.clear();
		multiplier++;
	}

	if( transformedPolys.size() == states.size() ) {
		std::cout << "Puzzle solved! Drawing solution..." << std::endl;
	}
	else {
		std::cout << "Puzzle solution incomplete. Drawing polygons..." << std::endl;
	}
	// Draw the transformed polygons on the completed puzzle
	for( int i = 0; i < transformedPolys.size(); i++ ) {
		Scalar color = Scalar(rng.uniform(0, 255), rng.uniform(0, 255), rng.uniform(0, 255));
		drawContours(statesMasked, states, i, color, 50);
		drawContours(sol, transformedPolys, i, color, 50);
	}

	// Resize the image. The original resolution is huge.
	Size size(0,0);
	resize(sol, sol, size, 0.15, 0.15);

	
	resize(statesMasked, statesMasked, size, 0.10, 0.10);
	namedWindow("Puzzle Pieces", CV_WINDOW_AUTOSIZE);
	imshow("Puzzle Pieces", statesMasked);

	// Create a window and display our solved puzzle in it.
	namedWindow("Solved Puzzle", CV_WINDOW_AUTOSIZE);
	imshow("Solved Puzzle", sol);
}


vector<vector<Point>> getPieces(vector<vector<Point>> contours) {
	vector<vector<Point>> pieces;
	for (int i = 0; i < contours.size(); i++)
	{
		// If the area of the contour is large enough we assume it is a full puzzle piece.
		// 1000 was determined to be sufficient through experimentation.
		// This may need to be adjusted for different puzzles and images of different resolutions.
		if( contourArea(contours[i], true ) > 1000 ) {
			pieces.push_back(contours[i]);
		}
	}
	return pieces;
}