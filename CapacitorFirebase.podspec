
  Pod::Spec.new do |s|
    s.name = 'CapacitorFirebase'
    s.version = '0.0.1'
    s.summary = 'plugin de Firebase adaptado para capacitor'
    s.license = 'MIT'
    s.homepage = 'https://github.com/dariofacundodasilva/capacitorFirebase.git'
    s.author = 'dariofacundodasilva'
    s.source = { :git => 'https://github.com/dariofacundodasilva/capacitorFirebase.git', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end