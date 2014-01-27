cs106i
======
Committing changes
------------------
While you're working on your code, it's important to save your state at various points so you can go back in case you get your code in an unworking state. Here's a simple workflow for saving your code:

```
git add FILES_HERE
```
You don't necessarily have to add all your files + changes to a commit.  Choose which files you'd like to save state for using this command.

```
git commit -m "YOUR COMMIT MESSAGE HERE"
```
Add a message in the present tense that describes your change.  For instance, "Implement turnRight, a method to have Karel turn right"

(Continue working on code)

As a best practice, commit early and commit often!

Pushing to Github
-----------------
Github is awesome in letting you store your code on their servers so that anyone can use / view it (like myself when I grade your assignments).  To push your code to the server, use git push

```
git push REMOTE_NAME BRANCH_NAME
```
By default, your own repository will have 'origin' as the remote name.  Also, you can specify a branch name or just use 'HEAD' to indicate your current branch.  A common default command you can use to push your changes is:

```
git push origin HEAD
```

Grabbing new assignments
------------------------
I'll be uploading the starter files for all the assignments on my repository for you to checkout.  First, make sure that you've added my repository as a remote (you only need to do this once):

```
git remote add isaac https://github.com/isyiwang/cs106i.git
```

To grab the latest changes from my repository:
```
git fetch isaac
```

The branches I'll have uploaded will just have the starter code.  To checkout the code:
```
git checkout -b assignment1 isaac/assignment1
```
Now your directory should have the starter code.  Happy coding + committing!
