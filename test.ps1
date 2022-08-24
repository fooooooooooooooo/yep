$jarFilter = "Yep-*.jar"
$sourcePath = "./target"
$destPath = "C:/Users/foo/velocity-test-server/paper/plugins"

$built = Get-ChildItem -Path $sourcePath -Recurse -Filter $jarFilter

if ($built.Count -eq 0) {
    Write-Host "No built jar found"
} else {
    Write-Host "Found jar: '$built'"
}

# Remove existing filtered jars in plugins

$existingJars = Get-ChildItem -Path $destPath -Filter $jarFilter

foreach ($existingJar in $existingJars) {
    Write-Host "Removing existing jar: '$destPath/$existingJar'"
    Remove-Item -Path $existingJar -Force -ErrorAction SilentlyContinue
}

# Copy built jar to plugins

Copy-Item -Path "$sourcePath/$built" -Destination $destPath -Force -ErrorAction Stop

Write-Host "Copied jar to '$destPath'"
