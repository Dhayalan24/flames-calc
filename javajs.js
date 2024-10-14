function countCommonLetters(name1, name2) {
    name1 = name1.toLowerCase().replace(/\s+/g, '');
    name2 = name2.toLowerCase().replace(/\s+/g, '');

    let sb1 = name1.split('');
    let sb2 = name2.split('');

    for (let i = 0; i < sb1.length; i++) {
        for (let j = 0; j < sb2.length; j++) {
            if (sb1[i] === sb2[j]) {
                sb1.splice(i, 1);
                sb2.splice(j, 1);
                i--;
                break;
            }
        }
    }

    return sb1.length + sb2.length;
}

function calculateFlamesResult(count) {
    let flames = "FLAMES";

    while (flames.length > 1) {
        let index = (count % flames.length) - 1;
        if (index >= 0) {
            flames = flames.slice(0, index) + flames.slice(index + 1);
        } else {
            flames = flames.slice(0, flames.length - 1);
        }
    }

    return flames.charAt(0);
}

function getFlamesImage(letter) {
    switch (letter) {
        case 'F': return "images/friends.webp";
        case 'L': return "images/love.webp";
        case 'A': return "images/Affection.webp";
        case 'M': return "images/marriage.webp";
        case 'E': return "images/enemies.webp";
        case 'S': return "images/siblings.webp";
        default: return "images/unknown.png";
    }
}


function calculateFlames() {
    const name1 = document.getElementById('name1').value;
    const name2 = document.getElementById('name2').value;

    const count = countCommonLetters(name1, name2);
    const resultLetter = calculateFlamesResult(count);

    const resultImageSrc = getFlamesImage(resultLetter);
    const flamesImage = document.getElementById('flamesImage');

    flamesImage.src = resultImageSrc;
    flamesImage.style.display = 'block';  // Show the image
}
