package com.madao.plugin.utils;

public class AsyncUtil {

	public static String getAsyncUtil() {
		return "import org.springframework.beans.factory.annotation.Value;\n" +
				"import org.springframework.http.HttpHeaders;\n" +
				"import org.springframework.http.HttpStatus;\n" +
				"import org.springframework.http.ResponseEntity;\n" +
				"import org.springframework.stereotype.Component;\n" +
				"import reactor.core.publisher.Flux;\n" +
				"import reactor.core.publisher.Mono;\n" +
				"import reactor.core.scheduler.Scheduler;\n" +
				"import reactor.core.scheduler.Schedulers;\n" +
				"\n" +
				"import java.util.List;\n" +
				"import java.util.concurrent.Callable;\n" +
				"import java.util.stream.Stream;\n" +
				"\n" +
				"@Component\n" +
				"public class AsyncUtil {\n" +
				"\n" +
				"    private Scheduler scheduler;\n" +
				"\n" +
				"    public AsyncUtil(@Value(\"${async.max-pool-size:50}\")Integer availableThreads) {\n" +
				"        //this.scheduler = Schedulers.fromExecutor(Executors.newFixedThreadPool(availableThreads));\n" +
				"        //this.scheduler = Schedulers.elastic();\n" +
				"        this.scheduler = Schedulers.elastic();\n" +
				"    }\n" +
				"\n" +
				"\n" +
				"    /* === MONO part === */\n" +
				"\n" +
				"    public <T> Mono<T> asyncMono(Callable<T> callable) {\n" +
				"        return Mono.fromCallable(callable).publishOn(scheduler);\n" +
				"    }\n" +
				"\n" +
				"    public static <X> Mono<ResponseEntity<X>> wrapOrNotFound(Mono<X> maybeResponse) {\n" +
				"        return wrapOrNotFound(maybeResponse, null);\n" +
				"    }\n" +
				"\n" +
				"    public static <X> Mono<ResponseEntity<X>> wrapOrNotFound(Mono<X> maybeResponse, HttpHeaders header) {\n" +
				"        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))\n" +
				"            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));\n" +
				"    }\n" +
				"\n" +
				"\n" +
				"    /* === FLUX part === */\n" +
				"\n" +
				"    public <T> Flux<T> asyncFlux(List<T> list) {\n" +
				"        if(list==null) return null;\n" +
				"        return Flux.fromStream(list.stream()).publishOn(scheduler);\n" +
				"    }\n" +
				"\n" +
				"    public <T> Flux<T> asyncFlux(Stream<T> stream) {\n" +
				"        if(stream==null) return null;\n" +
				"        return Flux.fromStream(stream).publishOn(scheduler);\n" +
				"    }\n" +
				"\n" +
				"    public <T> Flux<T> asyncFlux(Iterable<T> iterable) {\n" +
				"        if(iterable==null) return null;\n" +
				"        return Flux.fromIterable(iterable).publishOn(scheduler);\n" +
				"    }\n" +
				"\n" +
				"    public <T> Flux<T> asyncFlux(T[] array) {\n" +
				"        if(array==null) return null;\n" +
				"        return Flux.fromArray(array).publishOn(scheduler);\n" +
				"    }\n" +
				"\n" +
				"}";
	}
}
