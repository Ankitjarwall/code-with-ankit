import React from 'react';
import './Signup.css';

export default function Signup() {
  return (
    <div>
      <h2>Sign Up</h2>

      <div>
        <form>
          <div>
            <img src="img_avatar2.png" alt="Avatar" class="avatar" />
          </div>

          <div>
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="uname" required />

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" required />

            <button type="submit">Login</button>
            <label>
              <input type="checkbox" checked="checked" name="remember" /> Remember me
            </label>

            <div>
              <button type="button">Cancel</button>
              <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
          </div>
        </form>
      </div>

    </div>
  )
}
