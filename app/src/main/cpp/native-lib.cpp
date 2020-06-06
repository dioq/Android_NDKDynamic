#include <jni.h>
#include <string>
#include <stdio.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_my_ndkdynamicdemo_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    // TODO: implement stringFromJNI()
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

/************************************************** 以下是动态注册 *******************************************************/
#define JNI_REG_CLASS "com/my/ndkdynamicdemo/MainActivity" // path of Java file

JNIEXPORT jstring JNICALL get_hello(JNIEnv *env, jobject obj) {
    return env->NewStringUTF("hello from jni dynamic");
}

JNIEXPORT jint JNICALL meaning_of_the_universe(JNIEnv *env, jobject obj) {
    return 42;
}

static JNINativeMethod g_methods[] = {
        {"getHello",             "()Ljava/lang/String;", (void *) get_hello},
        {"meaningOfTheUniverse", "()I",                  (void *) meaning_of_the_universe},
};

// must define this function
JNIEXPORT int JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    jclass javaClass = env->FindClass(JNI_REG_CLASS);
    if (javaClass == NULL) {
        return JNI_ERR;
    }

    int method_count = sizeof(g_methods) / sizeof(g_methods[0]);
    if (env->RegisterNatives(javaClass, g_methods, method_count) < 0) {
        return JNI_ERR;
    }

    return JNI_VERSION_1_6;
}
