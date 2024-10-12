
Pod::Spec.new do |s|
  s.name         = "RNHinaCloudModule"
  s.version      = "0.0.1"
  s.summary      = "The official React Native SDK of Hina."
  s.description  = <<-DESC
                  海纳嗨数 RN 组件
                   DESC
  s.homepage     = "https://hicloud.hinadt.com"
  s.license      = { :type => "MIT" }
  s.author       = { "dequal" => "denghao@hinadt.com" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "git@git.hinadt.com:hina-cloud-sdk/hina-react-native-lib.git", :tag => "v#{s.version}" }
  s.source_files = "ios/*.{h,m}"
  s.requires_arc = true
  s.dependency   "React"
  s.dependency   "HinaCloudSDK", ">= 4.0.6"

end
