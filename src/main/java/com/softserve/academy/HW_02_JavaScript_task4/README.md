# Homework 02 JavaScript

**Task 4:** Create a script to validate a web form. Your script should include the following functionalities and checks:
- **Required Fields Validation**: Ensure that all required fields are filled out. For example, fields like 'Name', 'Email', and 'Password' should not be left empty.
- **Email Format Validation**: Validate the email address format to ensure it meets standard email formatting (e.g., user@example.com). Use regular expressions to check if the email entered by the user is in the correct format.
- **Password Strength Check**: Implement a password strength validator. The password should meet certain criteria to be considered strong, such as a minimum length (e.g., 8 characters), the inclusion of uppercase and lowercase letters, numbers, and special characters.
- **Confirm Password Matching**: If there is a 'Confirm Password' field, check that it matches the 'Password' field.

## How to test
1. Open the `hw_02_task4.html` file with the script in your browser.
2. Try submitting the form with:
   - Empty fields → You should see messages indicating that the field is required.  
   - Invalid email → You should see a message indicating that the email format is incorrect.  
   - Weak password → You should see a message indicating that the password does not meet strength requirements.  
   - Mismatched `Password` and `Confirm Password` → You should see a message indicating that the passwords do not match.  
3. Correct all fields and click submit → The form should be accepted.


