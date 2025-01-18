import subprocess

import os

# Define paths based on the environment
if os.name == "nt":  # Windows
    debugger_command = r'"C:\Program Files\Calibre2\calibre-debug.exe"'
    script_path = r"C:\Users\2020\IdeaProjects\TestAutomationProject\src\test\java\apiTest\apiTesting.py"
else:  # Linux (GitHub Actions)
    debugger_command = "calibre-debug"
    script_path = "src/test/java/apiTest/apiTesting.py"

# Path to calibre-debug.exe and script
#debugger_command = r'"C:\Program Files\Calibre2\calibre-debug.exe"'
#script_path = r"C:\Users\2020\IdeaProjects\TestAutomationProject\src\test\java\apiTest\apiTesting.py"

# Command to run
command = f'{debugger_command} -e {script_path}'

try:
    # Run the command and capture output
    result = subprocess.run(
        command,
        shell=True,
        capture_output=True,
        text=True,
        check=True
    )

    # Print the output
    print("Command Output (stdout):")
    print(result.stdout)  # Captures test results and success messages
    print("\nCommand Errors (stderr):")
    print(result.stderr)  # Captures errors and assertion failures

except subprocess.CalledProcessError as e:
    # Handle non-zero exit codes (e.g., failed assertions)
    print("Command failed with a non-zero exit code:")
    print(f"Standard Output:\n{e.stdout}")
    print(f"Standard Error:\n{e.stderr}")

except subprocess.TimeoutExpired as e:
    # Handle timeout
    print("Command timed out.")
    print(e)

except Exception as e:
    # Handle any other exceptions
    print("An unexpected error occurred:")
    print(e)



