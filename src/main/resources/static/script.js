async function fetchKeypad() {
    try {
        const response = await fetch('/keypad');
        const keypad = await response.json();

        const keypadContainer = document.querySelector('.keypad');
        keypadContainer.innerHTML = '';

        keypad.forEach(value => {
            const button = document.createElement('button');
            button.setAttribute('data-value', value);
            button.style.backgroundImage = `url('keypad/_${value}.png')`;
            button.addEventListener('click', async () => {
                try {
                    const response = await fetch(`/hash/${value}`);
                    const hash = await response.text();

                    // 서버에 해시값을 전송
                    const verificationResponse = await fetch('/verify', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ hash })
                    });
                    const result = await verificationResponse.text();
                    console.log(result);

                    // 해시값을 콘솔에 출력
                    console.log(`Hash: ${hash}`);
                } catch (error) {
                    console.error('Error fetching hash:', error);
                }
            });
            keypadContainer.appendChild(button);
        });
    } catch (error) {
        console.error('Error fetching keypad:', error);
    }
}

fetchKeypad();
