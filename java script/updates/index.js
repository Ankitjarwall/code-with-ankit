const jsonfile = require('jsonfile');
const moment = require('moment');
const simpleGit = require('simple-git')();
const FILE_PATH = './data.json';

// Function to generate a random integer between min and max (inclusive)
const getRandomInt = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

const makeCommit = n => {
    if (n === 0) {
        return simpleGit.push();  // Push after all commits
    }

    // Generate random week (x) and day (y)
    const x = getRandomInt(0, 54);  // Max 54 weeks in a year
    const y = getRandomInt(0, 6);   // 7 days a week
    const DATE = moment().subtract(2, 'y').add(1, 'd')
        .add(x, 'w').add(y, 'd').format();  // Calculate random date in past year

    const data = {
        date: DATE
    };

    console.log(`Committing for date: ${DATE}`);

    // Write to JSON file
    jsonfile.writeFile(FILE_PATH, data, (err) => {
        if (err) {
            console.error('Error writing file:', err);
            return;
        }

        // Add file and commit with --date flag
        simpleGit.add(FILE_PATH).commit("update", { '--date': DATE }, () => {
            // Recursive call with decremented n
            makeCommit(n - 1);
        });
    });
};

// Start committing process (500 commits)
makeCommit(500);
