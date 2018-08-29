package ex20

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

// C++: inline
// C:   static inline

// 예외 안정성을 고려한 함수

// Inline: 람다 파라미터를 가지고 있는 함수에서만 적용 가능한 기술
//         불필요한 코드를 제거하고 실행한 메소드 안으로 바이트 코드를 복사-붙여넣기 하는 기능이다.
inline fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

class IncThread(private val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    // static int n = 0;

    // lock을 직접 lock() 하거나 unlock() 할 경우
    // 문제점이 있습니다.
    //  => 임계영역 안에서 예외가 발생할 경우, unlock이 호출되지
    //     않는 문제가 발생합니다.
    override fun run() {
        // 1 - 1000000
        // for (int i = 1 ; i <= 1000000 ; ++i) - Kotlin X

        //  범위: Range Type
        /*
        for (e in 1..1000000) {
            lock.lock()
            n++
            lock.unlock()
        }
        */
        for (e in 1..1000000) {
//            withLock(lock) {
//                n++
//            }

            synchronized(lock) {
                n++
            }
        }
    }
}

fun main(args: Array<String>) {
    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n) // n?
}









