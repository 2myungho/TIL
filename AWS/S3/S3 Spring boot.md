## s3 만들기

버킷을 만들 때 모든 퍼블릭 엑세스 차단을 비활성화 한다.

* 퍼블릭 엑세스를 차단하고, 버킷 정책을 설정하는 방법을 사용

버킬 정책

```json
{
  "Version": "2012-10-17",
  "Id": "Policy1577077078140",
  "Statement": [
    {
      "Sid": "Stmt1577076944244",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::{버킷명}/*"
    }
  ]
}
```

