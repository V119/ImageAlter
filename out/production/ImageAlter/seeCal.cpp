#include "edu_xjtu_parallel_calculate_CalBySEE.h"
#include <intrin.h>

#define N 900000

float* pixelA;
float* pixelB;
float result[N];

JNIEXPORT jfloatArray JNICALL Java_edu_xjtu_parallel_calculate_CalBySEE_calPixel
(JNIEnv *jniEnv, jobject, jfloatArray pixA, jfloatArray pixB, jfloat fade) {
	__m128 numA;
	__m128 numB;
	__m128 mmFade;
	__m128 mmresult;

	int sizeA = jniEnv->GetArrayLength(pixA);
	int sizeB = jniEnv->GetArrayLength(pixB);

	const int smallSize = sizeA < sizeB ? sizeA : sizeB;
	const int bigSize = sizeA > sizeB ? sizeA : sizeB;

	jfloatArray resultArray = jniEnv->NewFloatArray(bigSize);

	pixelA = jniEnv->GetFloatArrayElements(pixA, NULL);
	pixelB = jniEnv->GetFloatArrayElements(pixB, NULL);

	jniEnv->ReleaseFloatArrayElements(pixA, pixelA, 0);
	jniEnv->ReleaseFloatArrayElements(pixB, pixelB, 0);

	for (int i = 0; i < smallSize; i += 4) {

		//init mmA and mmB
		numA = _mm_loadu_ps(pixelA + i);
		numB = _mm_loadu_ps(pixelB + i);
		
		mmFade = _mm_set1_ps(fade);
		
		//calculate
		mmresult = _mm_add_ps(
							_mm_mul_ps(
								_mm_sub_ps(numA, numB)
								, mmFade)
							, numB);		
		//
		_mm_storeu_ps(result + i, mmresult);
	}
	if (sizeA != sizeB) {
		if (sizeB == bigSize) {
			for (int i = smallSize; i < bigSize; i++) {
				result[i] = pixelB[i];
			}
		}
		else {
			for (int j = smallSize; j < bigSize; j++) {
				result[j] = pixelA[j];
			}
		}
	}
	
	jniEnv->SetFloatArrayRegion(resultArray, 0, bigSize, result);
	return resultArray;
}