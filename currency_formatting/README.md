@Formatting currencies in Java

**What’s the problem?** When dealing with money amounts in Java, the primitive double type works reasonably well, but has a couple of problems:
* The double type is based on a binary system, so it can exactly represent fractional numbers that can be expressed as negative powers of two, such as 0.125 (2-3). However, it cannot represent most currency values, which are expressed in positive and negative powers of 10. For example, 34.11 = 3x101+4x100+1x10-11x10-2 cannot be precisely expressed as any combination of powers of 2.
