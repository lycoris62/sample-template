# 공통 응답 가이드

```java
/**
 * 샘플 단건 조회
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/samples") // URL 자원은 복수형으로 사용
public class SampleController {

    @GetMapping("/{id}")
    public CommonResponse<GetSampleRes> getSample(@PathVariable("id") UUID id) {
        GetSampleRes response = sampleService.getSample(id);
        return CommonResponse.success(response);
    }
}
```

단건 조회

```json
{
  "code": 0,
  "message": "정상 처리 되었습니다.",
  "data": {
    "id": "b5c6b2bd-1c34-4dae-82cb-c69aa3d3a40d",
    "name": "샘플1",
    "money": 1000
  }
}
```

단건 조회 응답

```java

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/samples") // URL 자원은 복수형으로 사용
public class SampleController {

    /**
     * 샘플 리스트 조회
     */
    @GetMapping
    public CommonResponse<List<GetSampleRes>> getSample(
        @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        List<GetSampleRes> response = sampleService.getSampleList(pageable);
        return CommonResponse.success(response);
    }
}
```

리스트 조회

- 응답은 List 타입으로 했으나, 컨벤션에 맞게 수정 (Slice, Page, 커스텀 DTO 등)

```json
{
  "code": 0,
  "message": "정상 처리 되었습니다.",
  "data": [
    {
      "id": "ff4ad01f-03cb-45ba-a06c-3b4ccdb151d1",
      "name": "샘플1",
      "money": 1000
    },
    {
      "id": "9d39c398-2b1d-40f8-8720-888ca3165e24",
      "name": "샘플1",
      "money": 1000
    },
    {
      "id": "93598f62-12c4-4b9b-89e3-6b18c3ba19be",
      "name": "샘플1",
      "money": 1000
    }
  ]
}
```

리스트 조회 응답

```java

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/samples") // URL 자원은 복수형으로 사용
public class SampleController {

    /**
     * 샘플 생성
     */
    @PostMapping
    public CommonResponse<CreateSampleRes> createSample(@Validated @RequestBody CreateSampleReq request) {
        CreateSampleRes response = sampleService.createSample(request);
        return CommonResponse.success(response);
    }
}
```

생성

```json
{
  "code": 0,
  "message": "정상 처리 되었습니다.",
  "data": {
    "id": "ff4ad01f-03cb-45ba-a06c-3b4ccdb151d1",
    "name": "샘플1",
    "money": 1000
  }
}
```

생성 응답

```java

@PostMapping
public CommonResponse<CreateSampleRes> createSample(
    @Validated @RequestBody CreateSampleReq request,
    @LoginUser User user
) {
    CreateSampleRes response = sampleService.createSample(request, user);
    return CommonResponse.success(response);
}
```

- 선언부가 길어지는 경우, 줄바꿈을 통해 가독성을 높임

```java

@DeleteMapping("/{id}")
public CommonResponse<CommonEmptyRes> deleteSample(@PathVariable("id") UUID id) {
    sampleService.deleteSample(id);
    return CommonResponse.success();
}
```

삭제

- 특별히 응답이 필요하지 않은 경우 CommonEmptyRes 사용하여 응답

```json
{
  "code": 0,
  "message": "정상 처리 되었습니다.",
  "data": {}
}
```

빈 객체 응답 